/*
Problem 2 - Prime Checker
Obiettivo:
- Leggere un numero intero.
- Verificare se il numero è primo, cioè divisibile solo per 1 e per se stesso.

Spiegazione codice:
- La funzione isPrime controlla prima il caso base n < 2, che non è primo.
- Il ciclo prova i divisori da 2 fino alla radice quadrata di n.
- Se trova un divisore esatto, il numero non è primo e la funzione ritorna false.
- Se nessun divisore funziona, il numero è primo e la funzione ritorna true.
- La main legge l'input da tastiera e stampa il risultato finale.

Edge cases:
- 0, 1 e tutti i numeri negativi non sono primi.
- Numeri piccoli come 2 e 3 devono essere riconosciuti correttamente.
- Se l'input non è un intero valido, readLine()!!.toInt() può generare eccezione.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P2_PrimeChecker.kt -include-runtime -d P2_PrimeChecker.jar
2- Esegui il programma
    java -jar P2_PrimeChecker.jar
3- Inserisci i valori richiesti quando richiesto a schermo
*/

fun isPrime(n: Int): Boolean {
    // Caso base: sotto 2 non esistono numeri primi.
    if (n < 2) return false

    // Verifica i divisori fino alla radice quadrata, che è sufficiente per sapere se n è composto.
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

fun main() {
    print("Number: ")
    val n = readLine()!!.toInt()
    println(if (isPrime(n)) "$n is prime" else "$n is NOT prime")
}
