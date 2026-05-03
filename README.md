# A001959-2025-26

Repository didattico con esercizi Kotlin e progetti Android.

## Struttura

- `homework/`: esercizi Kotlin per lezioni (`L13`-`L24`), file `.kt` indipendenti.
- `ta_exercises/`: esercizi guidati di supporto.
- `textbook/`: progetti Android Studio dell'appendice del corso (`E01`-`E06`), ogni cartella e un progetto separato.

## Requisiti consigliati

- Kotlin `2.3.21`
- Java `17+`
- Android Studio (per `textbook/`)

Verifica rapida:

```bash
kotlinc -version
java -version
```

## Esecuzione manuale di un esercizio Kotlin

```bash
kotlinc homework/L13_RecyclerView/P1_AdapterImplementation.kt -include-runtime -d P1.jar
java -jar P1.jar
```

## Apertura progetti Android (`textbook`)

Apri direttamente una sottocartella `E0X_*` in Android Studio, per esempio:

```text
textbook/E02_Calculator
```

Non aprire `textbook/` come progetto unico.

## Note

- La repository ora usa un solo README (questo file).
- Script e report temporanei sono stati rimossi per mantenere la struttura pulita.

## Licenza

Materiale per uso didattico.
