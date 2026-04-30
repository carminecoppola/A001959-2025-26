

// Exercise 5 - Flow Basics - Create a simple flow and collect it.

package l07.exercise5   

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Flow = stream asincrono di dati
fun counter(max: Int) = flow {

    for (i in 1..max) {

        // emit invia un valore allo stream
        emit(i)

        // simulazione delay tra valori
        delay(200)
    }
}

fun main() = runBlocking {

    // collect = consuma i valori del flow
    counter(5).collect { value ->

        println("Received: $value")
    }
}