

// Exercise 3 - Types and Type Inference

package l01.exercise3

fun main() {

    val message = "Kotlin"   // tipo inferito: String
    val count   = 42         // tipo inferito: Int
    val ratio   = 3.14       // tipo inferito: Double
    val active  = true       // tipo inferito: Boolean

    // Interpoliamo tutte le variabili in un'unica stringa
    println("$message $count $ratio $active")
    // Output: Kotlin 42 3.14 true
}