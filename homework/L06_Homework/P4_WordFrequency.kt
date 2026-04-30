/*
Problem 4 - Word Frequency
Obiettivo:
- Leggere una frase, contare le occorrenze delle parole e stamparle ordinate per frequenza decrescente.

Spiegazione codice:
- Si legge una riga da input e si normalizza in lowercase().
- groupingBy + eachCount costruiscono la mappa parola -> conteggio.
- Gli entry vengono poi ordinate per valore decrescente e stampate.

Edge cases:
- Input vuoto genera lista con una stringa vuota dopo split;
- Punteggiatura non viene rimossa in questa implementazione semplice.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P4_WordFrequency.kt -include-runtime -d P4_WordFrequency.jar
2- Esegui il programma
    java -jar P4_WordFrequency.jar
3- Inserisci la stringa quando richiesto a schermo
*/

fun main() {
    print("Sentence: ")
    val words = readLine()!!.lowercase().split(" ")

    words.groupingBy { it }
        .eachCount()
        .entries
        .sortedByDescending { it.value }
        .forEach { (word, count) -> println("$word: $count") }
}
