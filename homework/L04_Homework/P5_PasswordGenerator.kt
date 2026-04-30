/*
Problem 5 - Password Generator
Obiettivo:
- Scrivere fun generatePassword(length, useUpper, useDigits, useSymbols).
- Generare una password scegliendo caratteri da un pool costruito dinamicamente.

Spiegazione codice:
- La variabile pool parte con le lettere minuscole.
- I flag booleani aggiungono maiuscole, cifre e simboli al pool.
- La password viene costruita pescando caratteri casuali con random().
- Il main mostra un esempio con i simboli attivi.

Edge cases:
- length troppo piccolo produce una password corta, perché non ci sono vincoli aggiuntivi.
- Se tutti i flag restano false, il pool contiene solo lettere minuscole.
- La generazione è random, quindi ogni esecuzione può produrre un output diverso.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P5_PasswordGenerator.kt -include-runtime -d P5_PasswordGenerator.jar
2- Esegui il programma
    java -jar P5_PasswordGenerator.jar
3- Questo esercizio non richiede input
*/

fun generatePassword(
    length: Int,
    useUpper: Boolean = true,
    useDigits: Boolean = true,
    useSymbols: Boolean = false
): String {
    var pool = "abcdefghijklmnopqrstuvwxyz"
    if (useUpper)   pool += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (useDigits)  pool += "0123456789"
    if (useSymbols) pool += "!@#$%^&*"

    return (1..length).map { pool.random() }.joinToString("")
}

fun main() {
    println(generatePassword(16, useSymbols = true))
}