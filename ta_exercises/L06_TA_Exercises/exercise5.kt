

// Exercise 5 - Extension Functions - Add isValidEmail() and truncate() to String.

package l06.exercise5

// Extension function su String
// Aggiunge un metodo senza modificare la classe originale
fun String.isValidEmail(): Boolean {

    // Controllo molto semplice (non production-ready)
    return this.contains('@') && this.contains('.')
}

// Extension function per troncare una stringa
fun String.truncate(
    max: Int,
    ellipsis: String = "..." // valore di default
): String {

    return if (this.length <= max) {
        // Se la stringa è corta, la restituisco intera
        this
    } else {
        // Altrimenti, prendo i primi "max" caratteri
        // e aggiungo i puntini
        this.take(max) + ellipsis
    }
}

fun main() {

    val email1 = "alice@example.com"
    val email2 = "not-an-email"

    // Test validazione email
    println(email1.isValidEmail()) // true
    println(email2.isValidEmail()) // false

    // Test truncate
    val text = "Hello, World!"
    println(text.truncate(7)) // Hello, ...
}