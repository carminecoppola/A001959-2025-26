/*
Problem 5 - Coroutine Scope

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_CoroutineScope.kt -include-runtime -d P5_CoroutineScope.jar
2. Run the program:
   java -jar P5_CoroutineScope.jar
*/

import kotlinx.coroutines.*

fun main() = runBlocking {
    coroutineScope {
        (1..5).forEach { id ->
            launch {
                delay((Math.random() * 500).toLong())
                println("Coroutine $id done")
            }
        }
    }

    println("All done")
}