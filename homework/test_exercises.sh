#!/usr/bin/env bash

set -o pipefail

usage() {
  cat <<'EOF'
Usage:
  homework/test_exercises.sh <path>

Examples:
  homework/test_exercises.sh homework/L13_RecyclerView
  homework/test_exercises.sh homework/L13_RecyclerView/P1_AdapterImplementation.kt
  homework/test_exercises.sh homework

What it does:
  - Finds all .kt files inside <path> (or uses the single file).
  - For each file, looks for runnable entry points:
    - fun main() with no args
    - fun demo...() with no args
    - fun demo...(x: File) with one File arg (auto temp dir)
  - Generates temporary runner code when needed.
  - Compiles with kotlinc and runs with java -jar.
  - Writes logs under homework/reports.
EOF
}

if [[ $# -ne 1 ]]; then
  usage
  exit 1
fi

TARGET="$1"

if ! command -v kotlinc >/dev/null 2>&1; then
  echo "ERROR: kotlinc not found in PATH"
  exit 1
fi

if ! command -v java >/dev/null 2>&1; then
  echo "ERROR: java not found in PATH"
  exit 1
fi

if [[ ! -e "$TARGET" ]]; then
  echo "ERROR: path not found: $TARGET"
  exit 1
fi

ROOT_DIR="$(pwd)"
OUT_DIR="$ROOT_DIR/homework/out"
REPORTS_DIR="$ROOT_DIR/homework/reports"
RUNNER_DIR="$ROOT_DIR/homework/.generated_runners"

mkdir -p "$OUT_DIR" "$REPORTS_DIR" "$RUNNER_DIR"

FILES=()
if [[ -d "$TARGET" ]]; then
  while IFS= read -r file; do
    FILES+=("$file")
  done < <(find "$TARGET" -type f -name "*.kt" \
      ! -path "*/runners/*" \
      ! -path "*/.generated_runners/*" \
      ! -path "*/out/*" \
      ! -path "*/reports/*" | sort)
else
  if [[ "$TARGET" != *.kt ]]; then
    echo "ERROR: file is not a .kt source: $TARGET"
    exit 1
  fi
  FILES+=("$TARGET")
fi

if [[ ${#FILES[@]} -eq 0 ]]; then
  echo "No Kotlin files found in: $TARGET"
  exit 0
fi

RESULTS_FILE="$REPORTS_DIR/summary_$(date +%Y%m%d_%H%M%S).txt"

echo "Exercise test summary" > "$RESULTS_FILE"
echo "Target: $TARGET" >> "$RESULTS_FILE"
echo "Date: $(date)" >> "$RESULTS_FILE"
echo "" >> "$RESULTS_FILE"

PASS_COUNT=0
FAIL_COUNT=0
SKIP_COUNT=0

sanitize_name() {
  local input="$1"
  echo "$input" | sed 's/[^a-zA-Z0-9._-]/_/g'
}

extract_package() {
  local source_file="$1"
  grep -E '^[[:space:]]*package[[:space:]]+[A-Za-z0-9_.]+' "$source_file" | head -n1 | sed -E 's/^[[:space:]]*package[[:space:]]+([A-Za-z0-9_.]+).*/\1/'
}

extract_zero_arg_demo_functions() {
  local source_file="$1"
  grep -E '^[[:space:]]*fun[[:space:]]+demo[A-Za-z0-9_]*[[:space:]]*\([[:space:]]*\)' "$source_file" | sed -E 's/^[[:space:]]*fun[[:space:]]+([A-Za-z0-9_]+).*/\1/'
}

extract_file_arg_demo_functions() {
  local source_file="$1"
  grep -E '^[[:space:]]*fun[[:space:]]+demo[A-Za-z0-9_]*[[:space:]]*\([[:space:]]*[A-Za-z0-9_]+[[:space:]]*:[[:space:]]*File[[:space:]]*\)' "$source_file" | sed -E 's/^[[:space:]]*fun[[:space:]]+([A-Za-z0-9_]+).*/\1/'
}

has_main_no_args() {
  local source_file="$1"
  grep -Eq '^[[:space:]]*fun[[:space:]]+main[[:space:]]*\([[:space:]]*\)' "$source_file"
}

for file in "${FILES[@]}"; do
  rel_file="${file#$ROOT_DIR/}"
  safe_rel="$(sanitize_name "$rel_file")"
  file_dir="$(dirname "$file")"
  jar_file="$OUT_DIR/${safe_rel}.jar"
  compile_log="$REPORTS_DIR/${safe_rel}.compile.log"
  run_log="$REPORTS_DIR/${safe_rel}.run.log"
  runner_file="$RUNNER_DIR/Runner_${safe_rel}.kt"

  compile_sources=()
  while IFS= read -r src; do
    compile_sources+=("$src")
  done < <(find "$file_dir" -maxdepth 1 -type f -name "*.kt" \
      ! -path "*/runners/*" \
      ! -path "*/.generated_runners/*" \
      ! -path "*/out/*" \
      ! -path "*/reports/*" | sort)

  if [[ ${#compile_sources[@]} -eq 0 ]]; then
    compile_sources=("$file")
  fi

  echo "Testing: $rel_file"

  package_name="$(extract_package "$file")"
  demo_no_args=()
  demo_file_arg=()

  while IFS= read -r fn; do
    [[ -n "$fn" ]] && demo_no_args+=("$fn")
  done < <(extract_zero_arg_demo_functions "$file")

  while IFS= read -r fn; do
    [[ -n "$fn" ]] && demo_file_arg+=("$fn")
  done < <(extract_file_arg_demo_functions "$file")

  if has_main_no_args "$file"; then
    if kotlinc "${compile_sources[@]}" -include-runtime -d "$jar_file" >"$compile_log" 2>&1; then
      if java -jar "$jar_file" >"$run_log" 2>&1; then
        echo "PASS | $rel_file | Used existing main()" >> "$RESULTS_FILE"
        PASS_COUNT=$((PASS_COUNT + 1))
      else
        echo "FAIL | $rel_file | Runtime error (see $run_log)" >> "$RESULTS_FILE"
        FAIL_COUNT=$((FAIL_COUNT + 1))
      fi
    else
      echo "FAIL | $rel_file | Compile error (see $compile_log)" >> "$RESULTS_FILE"
      FAIL_COUNT=$((FAIL_COUNT + 1))
    fi
    continue
  fi

  if [[ ${#demo_no_args[@]} -eq 0 && ${#demo_file_arg[@]} -eq 0 ]]; then
    echo "SKIP | $rel_file | No main() or supported demo function" >> "$RESULTS_FILE"
    SKIP_COUNT=$((SKIP_COUNT + 1))
    continue
  fi

  {
    if [[ -n "$package_name" ]]; then
      echo "package $package_name"
      echo
    fi

    echo "import java.io.File"
    echo
    echo "private fun printResult(name: String, value: Any?) {"
    echo "    println(\"Result from \" + name + \":\")"
    echo "    when (value) {"
    echo "        is Iterable<*> -> value.forEach { println(it) }"
    echo "        is Map<*, *> -> value.forEach { (k, v) -> println(\"\" + k + \"=\" + v) }"
    echo "        else -> println(value)"
    echo "    }"
    echo "    println()"
    echo "}"
    echo
    echo "fun main() {"
    echo "    val tempRoot = File(\"homework/out/tmp_data\").apply { mkdirs() }"

    for fn in "${demo_no_args[@]}"; do
      echo "    println(\"=== $fn() ===\")"
      echo "    val result_$fn = $fn()"
      echo "    printResult(\"$fn\", result_$fn)"
    done

    for fn in "${demo_file_arg[@]}"; do
      echo "    println(\"=== $fn(File) ===\")"
      echo "    val dir_$fn = File(tempRoot, \"$fn\").apply { mkdirs() }"
      echo "    val result_$fn = $fn(dir_$fn)"
      echo "    printResult(\"$fn\", result_$fn)"
    done

    echo "}"
  } > "$runner_file"

  if kotlinc "${compile_sources[@]}" "$runner_file" -include-runtime -d "$jar_file" >"$compile_log" 2>&1; then
    if java -jar "$jar_file" >"$run_log" 2>&1; then
      echo "PASS | $rel_file | Ran demo function(s)" >> "$RESULTS_FILE"
      PASS_COUNT=$((PASS_COUNT + 1))
    else
      echo "FAIL | $rel_file | Runtime error (see $run_log)" >> "$RESULTS_FILE"
      FAIL_COUNT=$((FAIL_COUNT + 1))
    fi
  else
    echo "FAIL | $rel_file | Compile error (see $compile_log)" >> "$RESULTS_FILE"
    FAIL_COUNT=$((FAIL_COUNT + 1))
  fi
done

echo "" >> "$RESULTS_FILE"
echo "Totals: PASS=$PASS_COUNT FAIL=$FAIL_COUNT SKIP=$SKIP_COUNT" >> "$RESULTS_FILE"

echo
echo "Done."
echo "PASS=$PASS_COUNT FAIL=$FAIL_COUNT SKIP=$SKIP_COUNT"
echo "Summary: $RESULTS_FILE"
echo "Detailed logs: $REPORTS_DIR"
