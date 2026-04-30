/*
Problem 2 - Safe List Access
Obiettivo:
- Scrivere una funzione safeGet(list: List<Int>, index: Int): Int?
- Restituire null se l'indice è fuori dai limiti.

Spiegazione codice:
- La funzione restituisce un Int? perché un accesso non valido deve produrre null.
- getOrNull(index) controlla in modo sicuro i limiti della lista.
- Questo evita eccezioni come IndexOutOfBoundsException.
- Nel main si verifica sia un indice valido sia uno non valido.

Edge cases:
- Indice negativo: getOrNull restituisce null.
- Indice maggiore dell'ultima posizione: getOrNull restituisce null.
- Lista vuota: ogni accesso restituisce null.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P2_SafeListAccess.kt -include-runtime -d P2_SafeListAccess.jar
2- Esegui il programma
    java -jar P2_SafeListAccess.jar
3- Questo esercizio non richiede input
*/

fun safeGet(list: List<Int>, index: Int): Int? = list.getOrNull(index)

fun main() {
    val nums = listOf(10, 20, 30)
    println(safeGet(nums, 1))   // 20
    println(safeGet(nums, 99))  // null
}