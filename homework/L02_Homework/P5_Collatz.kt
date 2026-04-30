/*
Problem 5 - Collatz Conjecture
Obiettivo:
- Leggere n.
- Applicare la regola di Collatz fino a raggiungere 1.
- Contare gli step necessari.

Spiegazione codice:
- La main legge il valore iniziale come Long per gestire numeri più grandi di Int.
- Un ciclo while continua finché n non diventa 1.
- Ad ogni iterazione viene applicata la regola corretta con un controllo sul resto della divisione per 2.
- Ogni passaggio incrementa il contatore steps.
- Alla fine viene stampato il numero totale di passaggi necessari per arrivare a 1.

Edge cases:
- Se n è già 1, il ciclo non parte e gli step restano 0.
- Se n è 0, la sequenza non raggiunge mai 1 con queste regole, quindi l'esercizio assume un input valido maggiore di 0.
- Numeri negativi non sono gestiti dalla formulazione classica della congettura e possono portare a sequenze non desiderate.
- Input non numerico: readLine()!!.toLong() può fallire.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P5_Collatz.kt -include-runtime -d P5_Collatz.jar
2- Esegui il programma
    java -jar P5_Collatz.jar
3- Inserisci i valori richiesti quando richiesto a schermo
*/

fun main() {
    print("n: ")
    var n = readLine()!!.toLong()
    var steps = 0
    while (n != 1L) {
        n = if (n % 2 == 0L) n / 2 else n * 3 + 1
        steps++
    }
    println("Reached 1 in $steps steps")
}
