/*
Problem 1 - Safe Division
Obiettivo:
- Scrivere una funzione safeDivide(a: Int, b: Int): Int?
- Restituire null invece di generare un errore quando b == 0.

Spiegazione codice:
- La funzione usa Int? come tipo di ritorno per rappresentare un risultato valido oppure null.
- Il controllo if verifica il divisore prima di eseguire la divisione.
- Se b è 0, il risultato è null.
- Nel main si mostra anche l'uso dell'Elvis operator ?: per fornire un valore di fallback.

Edge cases:
- Divisione per zero: deve restituire null.
- Divisione tra numeri negativi o positivi: segue il comportamento standard degli interi.
- Il valore null viene gestito esplicitamente nel main con ?:.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P1_SafeDivision.kt -include-runtime -d P1_SafeDivision.jar
2- Esegui il programma
    java -jar P1_SafeDivision.jar
3- Questo esercizio non richiede input
*/

fun safeDivide(a: Int, b: Int): Int? = if (b == 0) null else a / b

fun main() {
    println(safeDivide(10, 2))  // 5
    println(safeDivide(10, 0))  // null
    val result = safeDivide(10, 0) ?: -1
    println("Result: $result")  // Result: -1
}