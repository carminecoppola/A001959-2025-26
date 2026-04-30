/*
Problem 4 - List Statistics
Obiettivo:
- Usare funzioni di collezione per calcolare sum, average, max, min e count dei numeri pari.

Spiegazione codice:
- La lista nums contiene i valori su cui lavorare.
- sum(), average(), maxOrNull() e minOrNull() sono funzioni già disponibili sulla collezione.
- count con una lambda filtra gli elementi pari.
- Il main stampa i risultati in modo diretto.

Edge cases:
- Lista vuota: sum() e average() gestiscono il caso, mentre maxOrNull() e minOrNull() restituiscono null.
- Numeri negativi sarebbero gestiti correttamente dalle funzioni di collezione.
- Il conteggio dei pari usa il test modulo 2.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P4_ListStatistics.kt -include-runtime -d P4_ListStatistics.jar
2- Esegui il programma
    java -jar P4_ListStatistics.jar
3- Questo esercizio non richiede input
*/

fun main() {
    val nums = listOf(3, 7, 2, 9, 4, 6, 1, 8, 5)

    println("Sum: ${nums.sum()}")
    println("Avg: ${nums.average()}")
    println("Max: ${nums.maxOrNull()}")
    println("Min: ${nums.minOrNull()}")
    println("Even count: ${nums.count { it % 2 == 0 }}")
}