/*
Problem 3 - Number Pattern
Obiettivo:
- Leggere il numero di righe.
- Stampare un triangolo di asterischi crescente.

Spiegazione codice:
- La main legge quante righe stampare.
- Il primo ciclo for scorre le righe da 1 fino al valore richiesto.
- Il secondo ciclo interno stampa tanti "* " quanti sono gli elementi della riga corrente.
- Dopo ogni riga viene stampato un println() per andare a capo.
- Il risultato è un triangolo semplice e progressivo.

Edge cases:
- Se rows è 0, il ciclo non stampa nulla.
- Se rows è negativo, il range 1..rows non produce iterazioni e quindi non si stampa nulla.
- Se l'input non è numerico, readLine()!!.toInt() può fallire.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P3_NumberPattern.kt -include-runtime -d P3_NumberPattern.jar
2- Esegui il programma
    java -jar P3_NumberPattern.jar
3- Inserisci i valori richiesti quando richiesto a schermo
*/

fun main() {
    print("Rows: ")
    val rows = readLine()!!.toInt()
    for (row in 1..rows) {
        for (star in 1..row) print("* ")
        println()
    }
}
