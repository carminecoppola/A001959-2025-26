

// Exercise 2 - Parallel async - Fetch two items concurrently with async.

package l07.exercise2

import kotlinx.coroutines.*

// Funzione sospesa (può usare delay)
suspend fun fetchItem(id: Int): String {

    // Simula chiamata API
    delay(500)

    return "Item#$id"
}

fun main() = runBlocking {

    // async avvia coroutine che restituisce un valore (Deferred)
    val a = async {
        fetchItem(1)
    }

    val b = async {
        fetchItem(2)
    }

    // await() blocca finché il risultato non è pronto
    val resultA = a.await()
    val resultB = b.await()

    println("$resultA and $resultB")
}