

// Exercise 4 - While Loop - Guess the number

package l02.exercise4

fun main() {
    val secret = 7   // il numero segreto da indovinare
    var guess = 0    // tentativo corrente (inizializzato a 0, diverso da secret)
    var attempts = 0 // contatore dei tentativi

    // Il ciclo 'while' continua finché la condizione è vera.
    // Ad ogni iterazione generiamo un numero casuale nel range 1..10
    while (guess != secret) {
        guess = (1..10).random()  // genera un intero casuale tra 1 e 10 (inclusi)
        attempts++
        println("Guess $attempts: $guess")
    }

    // Quando usciamo dal ciclo, sappiamo con certezza che guess == secret
    println("Found in $attempts attempts!")

    // 💡 (1..10).random() è un metodo delle IntRange di Kotlin.
    //    L'output varia ad ogni esecuzione perché il numero è casuale.
}