

// Exercise 2 - VAL vs VAR

fun main() {
    // 'val' (value) → variabile immutabile, simile a 'final' in Java
    // Una volta assegnata, non può essere modificata
    val pi = 3.14159 

    println(pi) // Output: 3.14159

    // pi = 77 // Errore di compilazione: Val cannot be reassigned

    // println(pi)

    // 'var' (variable) → variabile mutabile, può essere riassegnata
    var counter = 0 

    println(counter)  // Output: 0

    counter = 10
    println(counter)  // Output: 10
}