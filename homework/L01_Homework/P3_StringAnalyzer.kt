/*
Problem 3 - String Analyzer
Obiettivo:
Dato un input stringa, stampare:
- lunghezza
- versione uppercase
- se contiene "kotlin" (case-insensitive)
- primo e ultimo carattere

Spiegazione codice:
- Si legge la stringa; se readLine() restituisce null, si usa stringa vuota.
- Si stampa lunghezza e versione uppercase.
- Per il controllo case-insensitive si converte in lowercase e si usa contains.
- Primo e ultimo carattere vengono stampati solo se la stringa non è vuota.

Edge cases:
- Stringa vuota: non si tenta accesso a primo/ultimo carattere.
- Input null: gestito con fallback a stringa vuota.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P3_StringAnalyzer.kt -include-runtime -d P3_StringAnalyzer.jar
2- Esegui il programma
    java -jar P3_StringAnalyzer.jar
3- Inserisci la stringa quando richiesto a schermo.

*/
fun main() {
    print("Enter a string: ")
    val s = readLine() ?: ""
    println("Length: ${s.length}")
    println("Uppercase: ${s.uppercase()}")
    println("Contains 'kotlin': ${s.lowercase().contains("kotlin")}")
    if (s.isNotEmpty()) {
        println("First: ${s.first()}, Last: ${s.last()}")
    }
}
