

// Exercise 3 - Dispatchers - Show how withContext switches threads.

package l07.exercise3

import kotlinx.coroutines.*

fun main() = runBlocking {

    // Thread principale
    println("Main: ${Thread.currentThread().name}")

    // Cambio contesto → thread IO
    withContext(Dispatchers.IO) {

        println("IO: ${Thread.currentThread().name}")
    }

    // Torna al thread originale
    println("Back to main")
}