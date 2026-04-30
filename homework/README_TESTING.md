# Homework Exercise Testing

Questo repository contiene file Kotlin separati. Per testarli in blocco usa:

```bash
chmod +x homework/test_exercises.sh
homework/test_exercises.sh <path>
```

Esempi:

```bash
homework/test_exercises.sh homework/L13_RecyclerView
homework/test_exercises.sh homework/L18_Coroutines_Flow
homework/test_exercises.sh homework
homework/test_exercises.sh homework/L13_RecyclerView/P1_AdapterImplementation.kt
```

## Cosa fa lo script

- Cerca i file `.kt` sotto il path indicato.
- Per ogni file prova a eseguire:
  - `fun main()` senza argomenti, oppure
  - funzioni `demo...()` senza argomenti, oppure
  - funzioni `demo...(x: File)` passando una directory temporanea.
- Compila con `kotlinc` e lancia con `java -jar`.
- Salva report e log in `homework/reports/`.

## Output e log

- Summary: `homework/reports/summary_YYYYMMDD_HHMMSS.txt`
- Compile log: `homework/reports/<file>.compile.log`
- Run log: `homework/reports/<file>.run.log`

Ogni file ha uno stato:

- `PASS`: compilato ed eseguito.
- `FAIL`: errore di compilazione o runtime.
- `SKIP`: nessuna entrypoint rilevata (`main` o `demo`).
