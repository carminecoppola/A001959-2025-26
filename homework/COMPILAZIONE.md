# Come Compilare e Testare gli Esercizi Kotlin

Questa cartella contiene 60 esercizi Kotlin organizzati per lezione. Puoi testarli in due modi:

1. **Modo rapido**: usando lo script automatico (consigliato)
2. **Modo manuale**: compilando file per file uno a uno

---

## 🚀 Modo Rapido: Lo Script Automatico

Lo script testa TUTTI i file in una cartella o sottocartella con un singolo comando.

### Come usare

Apri il terminale dalla cartella principale del progetto e digita:

```bash
homework/test_exercises.sh homework
```

**Oppure test una sola lezione:**

```bash
homework/test_exercises.sh homework/L13_RecyclerView
homework/test_exercises.sh homework/L18_Coroutines_Flow
```

**Oppure test un singolo file:**

```bash
homework/test_exercises.sh homework/L13_RecyclerView/P1_AdapterImplementation.kt
```

### Cosa succede

- Lo script trova tutti i file `.kt` nella cartella
- Compila ogni file con `kotlinc`
- Esegue il codice con `java -jar`
- Crea un report in `homework/reports/summary_*.txt`
- Mostra il risultato: `PASS`, `FAIL`, oppure `SKIP`

### Esempi di output

```
Testing: homework/L13_RecyclerView/P1_AdapterImplementation.kt
Testing: homework/L13_RecyclerView/P2_DiffUtilCallback.kt
...
Done.
PASS=60 FAIL=0 SKIP=0
Summary: homework/reports/summary_20260430_174249.txt
```

### Leggere i risultati dettagliati

Dopo il test, guarda i log in `homework/reports/`:

- `summary_*.txt` — resoconto finale
- `homework_*.compile.log` — errori di compilazione (se ci sono)
- `homework_*.run.log` — output del programma e errori di esecuzione

---

## 📝 Modo Manuale: Compilare File per File

Se preferisci capire passo per passo cosa succede, segui questa procedura.

### Prerequisiti

Assicurati di avere:

```bash
java -version
kotlinc -version
```

Se non sono installati, su macOS con Homebrew:

```bash
brew install openjdk kotlin
```

### Compilare un singolo file

**Passo 1:** Entra nella cartella principale del progetto

```bash
cd /path/to/A001959-2025-26
```

**Passo 2:** Scegli un file da testare, esempio `L13_RecyclerView/P1_AdapterImplementation.kt`

**Passo 3:** Compila il file Kotlin in un jar

```bash
mkdir -p homework/out
kotlinc homework/L13_RecyclerView/P1_AdapterImplementation.kt \
  -include-runtime \
  -d homework/out/P1.jar
```

**Cosa significa:**
- `mkdir -p homework/out` — crea la cartella per i jar compilati
- `kotlinc <file>` — compila il file Kotlin
- `-include-runtime` — include il runtime Kotlin nel jar (così puoi eseguire con `java`)
- `-d homework/out/P1.jar` — salva il risultato in questa posizione

**Passo 4:** Esegui il jar

```bash
java -jar homework/out/P1.jar
```

**Passo 5:** Leggi l'output

```
== Output P1_AdapterImplementation ==
[1] Primo elemento - Questo e il primo dato della lista
[2] Secondo elemento - Questo e il secondo dato della lista
```

### Compilare una lezione intera (manualmente)

Se vuoi compilare tutti i file di una lezione insieme:

```bash
mkdir -p homework/out
kotlinc homework/L13_RecyclerView/*.kt \
  -include-runtime \
  -d homework/out/L13_all.jar
java -jar homework/out/L13_all.jar
```

### Compilare TODO i file insieme

```bash
mkdir -p homework/out
kotlinc homework/**/*.kt \
  -include-runtime \
  -d homework/out/all_exercises.jar
java -jar homework/out/all_exercises.jar
```

**Nota:** Se la shell non espande `**`, usa:

```bash
find homework -name "*.kt" -not -path "*/runners/*" -not -path "*/.generated_runners/*" | xargs kotlinc -include-runtime -d homework/out/all.jar
```

---

## 🎯 Esempi Completi

### Esempio 1: Testare solo L18_Coroutines_Flow

```bash
homework/test_exercises.sh homework/L18_Coroutines_Flow
```

Output:
```
Testing: homework/L18_Coroutines_Flow/P1_ViewModelScope.kt
Testing: homework/L18_Coroutines_Flow/P2_FlowCollection.kt
...
Done.
PASS=5 FAIL=0 SKIP=0
```

### Esempio 2: Compilare e testare manualmente L20/P1

```bash
mkdir -p homework/out
kotlinc homework/L20_Secure_Data_Storage_Keystore_Encryption/P1_KeystoreSetup.kt \
  -include-runtime \
  -d homework/out/L20_P1.jar
java -jar homework/out/L20_P1.jar
```

### Esempio 3: Capire il primo file passo per passo

1. Apri il file
```bash
cat homework/L13_RecyclerView/P1_AdapterImplementation.kt
```

2. Leggi i commenti all'inizio — dicono cosa fa l'esercizio

3. Compila
```bash
kotlinc homework/L13_RecyclerView/P1_AdapterImplementation.kt -include-runtime -d homework/out/P1.jar
```

4. Esegui
```bash
java -jar homework/out/P1.jar
```

5. Guarda l'output

---

## 📂 Struttura delle Cartelle

```
homework/
├── L13_RecyclerView/           # P1, P2, P3, P4, P5
├── L14_ViewModel_LiveData/     # P1, P2, P3, P4, P5
├── L15_Room_Database/          # P1, P2, P3, P4, P5
├── ...
├── L24_Final_Project_Workshop_Secure_Android_App/
├── out/                        # ← JAR compilati (non committare)
├── reports/                    # ← Log e summary (non committare)
├── .generated_runners/         # ← Runner temporanei (non committare)
├── test_exercises.sh           # ← Script principale
└── README_TESTING.md           # ← Questa guida
```

---

## 🔧 Risoluzione Problemi

### Errore: "kotlinc: command not found"

Installa Kotlin:
```bash
brew install kotlin
```

### Errore: "java: command not found"

Installa Java:
```bash
brew install openjdk
```

### Errore: "main() not found" oppure "demo function not found"

Significa che il file non ha una funzione `main()` o una funzione `demo*()` da eseguire. Leggi i commenti del file per capire cosa fa.

### I JAR occupano troppo spazio

Elimina la cartella `homework/out/` in sicurezza (è sempre rigenerabile):
```bash
rm -rf homework/out/
```

### Voglio pulire tutto

```bash
rm -rf homework/out/ homework/reports/ homework/.generated_runners/
```

---

## 💡 Consigli

1. **Inizia con L13_RecyclerView** — sono i più semplici
2. **Leggi i commenti** nel file `.kt` prima di eseguirlo — spiegano cosa fa
3. **Usa lo script** per testare più file insieme, usa il **manuale** per capire step per step
4. **Guarda i file sorgente** — sono la documentazione migliore

---

## ✅ Checklist per Neofiti

- [ ] Ho installato `java` e `kotlinc`
- [ ] Ho capito come usare lo script automatico
- [ ] Ho capito come compilare un file manualmente
- [ ] Ho compilato il primo file `L13/P1` e visto l'output
- [ ] Ho testato una lezione intera con lo script
- [ ] Ho letto il `.gitignore` e so cosa non viene committato

Buon lavoro! 🎉
