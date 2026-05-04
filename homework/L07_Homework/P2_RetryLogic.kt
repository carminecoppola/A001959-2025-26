/*
Problem 2 - Retry Logic

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_RetryLogic.kt -include-runtime -d P2_RetryLogic.jar
2. Run the program:
   java -jar P2_RetryLogic.jar
*/

import kotlinx.coroutines.*

suspend fun <T> withRetry(times: Int, block: suspend () -> T): T {
    var lastException: Exception? = null

    repeat(times) { attempt ->
        try {
            return block()
        } catch (e: Exception) {
            lastException = e
            println("Attempt ${attempt + 1} failed")
        }
    }

    throw lastException!!
}

fun main() = runBlocking {
    var count = 0

    val result = withRetry(3) {
        if (count++ < 2) throw Exception("Not yet")
        else "Success!"
    }

    println(result)
}