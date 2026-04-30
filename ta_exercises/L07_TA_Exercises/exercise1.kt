

// Exercise 1 - launch vs Thread - Compare Thread.sleep (blocking) with delay (non-blocking).

package l07.exercise1

import kotlinx.coroutines.*

// runBlocking crea una coroutine che BLOCCA il thread principale
// Serve solo per test (NON in Android production)
fun main() = runBlocking {

    // launch avvia una coroutine NON bloccante
    launch {

        // delay = sospende la coroutine SENZA bloccare il thread
        delay(1000)

        println("Coroutine done")
    }

    // Questo viene eseguito subito (non aspetta la coroutine)
    println("Main continues immediately")

    // Aspettiamo abbastanza tempo per vedere l'output
    delay(2000)
}