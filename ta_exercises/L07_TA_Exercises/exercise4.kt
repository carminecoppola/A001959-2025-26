

// Exercise 4 - Coroutine Cancellation - Cancel a coroutine and see what happens.

package l07.exercise4

import kotlinx.coroutines.*

fun main() = runBlocking {

    // launch restituisce un Job (controllo della coroutine)
    val job = launch {

        repeat(10) { i ->

            println("Working $i")

            // Punto di sospensione → necessario per cancellazione cooperativa
            delay(300)
        }
    }

    // Aspettiamo un po'
    delay(1000)

    // Cancello la coroutine
    job.cancel()

    println("Cancelled")
}