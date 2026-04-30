/*
Problem 1 - FizzBuzz
Obiettivo:
- Stampare i numeri da 1 a 50.
- Per i multipli di 3 stampare "Fizz".
- Per i multipli di 5 stampare "Buzz".
- Per i multipli di entrambi stampare "FizzBuzz".

Spiegazione codice:
- Il ciclo for scorre tutti i valori da 1 a 50 inclusi.
- Il blocco when controlla le condizioni nell'ordine corretto: prima 15, poi 3, poi 5.
- Questo evita che un numero divisibile per 15 venga intercettato prima dai casi 3 o 5.
- Se nessuna condizione è vera, viene stampato il numero corrente.

Edge cases:
- I numeri multipli di 15 devono produrre "FizzBuzz" e non solo "Fizz" o "Buzz".
- Il range fisso 1..50 evita input invalido perché non richiede lettura da tastiera.
- La logica non dipende da valori negativi o nulli, quindi non ci sono casi di errore di input.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P1_FizzBuzz.kt -include-runtime -d P1_FizzBuzz.jar
2- Esegui il programma
    java -jar P1_FizzBuzz.jar
3- Questo esercizio non richiede input.
*/

fun main() {
    for (i in 1..50) {
        println(when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3  == 0 -> "Fizz"
            i % 5  == 0 -> "Buzz"
            else        -> "$i"
        })
    }
}
