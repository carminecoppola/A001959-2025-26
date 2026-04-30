

// Exercise 2 - Single Expression Functions - Convert a function to a single expression function

// Quando il corpo di una funzione è una singola espressione,
// Kotlin permette di omettere le parentesi graffe e il 'return',
// usando la sintassi con '=' (assignment form).
// Il tipo di ritorno può essere omesso se il compilatore riesce a inferirlo.

// Forma completa (stile Java)
fun square(n: Int): Int { return n * n }

// Forma a singola espressione con tipo esplicito (opzionale ma utile per chiarezza)
fun cube(n: Int): Int = n * n * n

// Forma a singola espressione con tipo inferito
// Il compilatore capisce da solo che il risultato è una String
fun greet(name: String) = "Hello, $name!"

fun main() {
    println(square(4))        // Output: 16
    println(cube(3))          // Output: 27
    println(greet("Kotlin"))  // Output: Hello, Kotlin!

    // 💡 La forma a singola espressione è molto usata in Kotlin per
    //    funzioni semplici e rende il codice più conciso e dichiarativo.
}