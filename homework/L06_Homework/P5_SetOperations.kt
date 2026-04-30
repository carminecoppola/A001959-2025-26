/*
Problem 5 - Set Operations
Obiettivo:
- Dati due set di student IDs, calcolare intersezione, solo classe A, solo calsse B e unione.

Spiegazione codice:
- Si usano le operazioni di set standard: intersect, subtract e union.
- I risultati sono stampati direttamente.

Edge cases:
- Set vuoti gestiti correttamente dalle operazioni di set.
- IDs duplicati non sono presenti in un Set.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P5_SetOperations.kt -include-runtime -d P5_SetOperations.jar
2- Esegui il programma
    java -jar P5_SetOperations.jar
3- Questo esercizio non richiede input
*/

fun main() {
    val classA = setOf(1, 2, 3, 4, 5)
    val classB = setOf(3, 4, 5, 6, 7)

    println("Both: ${classA.intersect(classB)}")
    println("Only A: ${classA.subtract(classB)}")
    println("Only B: ${classB.subtract(classA)}")
    println("Either: ${classA.union(classB)}")
}
