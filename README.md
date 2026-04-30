# 📘 Kotlin Homework Repository

This repository contains a collection of Kotlin exercises organized by lesson folders:

* `L01_Homework`
* `L02_Homework`
* `L03_Homework`
* `L04_Homework`
* `L05_Homework`
* ...

Each folder includes multiple `.kt` files, one per exercise, with:

* ✔ Correct implementations
* ✔ Clear comments (problem description, logic explanation, edge cases)
* ✔ Instructions to compile and run each file

---

## ⚙️ Kotlin Requirements

* **Kotlin version:** 2.3.21 (tested)

Check your installation:

```bash
kotlinc -version
```

---

## 🚀 Recommended Setup (SDKMAN)

The easiest way to manage Kotlin versions is using SDKMAN:

```bash
# initialize sdkman (if not already active)
source ~/.sdkman/bin/sdkman-init.sh

# install Kotlin (tested version)
sdk install kotlin 2.3.21

# use the tested version
sdk use kotlin 2.3.21

# verify installation
kotlinc -version
```

To enable SDKMAN automatically on every terminal session, add this line to your shell config (`~/.bashrc` or `~/.zshrc`):

```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

---

## 🧰 Alternative: Manual Installation

```bash
mkdir -p ~/kotlin-latest
cd ~/kotlin-latest

curl -L https://github.com/JetBrains/kotlin/releases/latest/download/kotlin-compiler.zip -o kotlin.zip
unzip kotlin.zip

# temporary PATH
export PATH="$PATH:$HOME/kotlin-latest/kotlinc/bin"

# add the export line to ~/.bashrc or ~/.zshrc for persistence
```

---

## ▶️ Compile and Run

Each Kotlin file can be compiled and executed from the terminal:

```bash
kotlinc File.kt -include-runtime -d File.jar
java -jar File.jar
```

Example:

```bash
kotlinc P1_BMICalculator.kt -include-runtime -d P1_BMICalculator.jar && java -jar P1_BMICalculator.jar
```

Some exercises that require input can also be tested with:

```bash
printf "input1\ninput2\n" | java -jar File.jar
```

---

## ⚠️ Compatibility Notes

Some solutions use modern Kotlin features, such as:

* `sumOf`
* `maxOrNull`, `minOrNull`
* `uppercase()`, `lowercase()`

If you are using an older Kotlin version, you may need to adapt:

| New Function    | Older Alternative   |
| --------------- | ------------------- |
| `sumOf { ... }` | `map { ... }.sum()` |
| `maxOrNull()`   | `max()`             |
| `minOrNull()`   | `min()`             |
| `uppercase()`   | `toUpperCase()`     |
| `lowercase()`   | `toLowerCase()`     |

---

## 🧠 Notes

* Code is intentionally kept close to the provided solutions
* Edge cases are documented but not always handled explicitly (to match assignment requirements)
* Each file is self-contained and independently executable

---

## 🏁 Goal

The goal of this repository is to:

* Practice Kotlin fundamentals
* Understand core programming concepts
* Build clean, readable, and maintainable code

---

## 📄 License

This repository is for educational purposes.
