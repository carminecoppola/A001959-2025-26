/*
Problem 1 - Sequential vs Parallel
Obiettivo:
- Confrontare il tempo necessario per eseguire due task da 1 secondo in sequenza e in parallelo.

Spiegazione codice:
- `runBlocking` crea un bridge tra codice sincrono e coroutines.
- `slowTask` è una `suspend function` che simula lavoro asincrono con `delay`.
- `measureTimeMillis` misura il tempo reale dei due approcci.
- `async/await` avvia ed attende le coroutine in parallelo.

Edge cases:
- Se una coroutine fallisce, l'intero blocco può terminare con eccezione.
- Se i task non vengono `await`-ati il risultato può non essere consumato correttamente.
- Il confronto ha senso solo con operazioni sospendibili, non con lavoro CPU puro.

Come eseguirlo da terminale con coroutines:
1. Scaricare la libreria:
    curl -L https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.7.3/kotlinx-coroutines-core-jvm-1.7.3.jar -o coroutines.jar
2. Compilare:
    kotlinc P1_SequentialVsParallel.kt -cp coroutines.jar -include-runtime -d P1_SequentialVsParallel.jar
3. Eseguire:
    java -cp "P1_SequentialVsParallel.jar:coroutines.jar" P1_SequentialVsParallelKt
*/

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun slowTask(name: String): String {
    delay(1000)
    return name
}

fun main() = runBlocking {
    val seq = measureTimeMillis {
        val a = slowTask("A")
        val b = slowTask("B")
        println("$a $b")
    }

    val par = measureTimeMillis {
        val a = async { slowTask("C") }
        val b = async { slowTask("D") }
        println("${a.await()} ${b.await()}")
    }

    println("Sequential: ${seq}ms, Parallel: ${par}ms")
}