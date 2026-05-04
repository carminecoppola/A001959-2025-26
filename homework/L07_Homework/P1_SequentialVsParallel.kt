/*
Problem 1 - Sequential vs Parallel

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_SequentialVsParallel.kt -include-runtime -d P1_SequentialVsParallel.jar
2. Run the program:
   java -jar P1_SequentialVsParallel.jar
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