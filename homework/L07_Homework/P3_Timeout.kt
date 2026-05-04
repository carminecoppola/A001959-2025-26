/*
Problem 3 - Timeout

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_Timeout.kt -include-runtime -d P3_Timeout.jar
2. Run the program:
   java -jar P3_Timeout.jar
*/

import kotlinx.coroutines.*

fun main() = runBlocking {
    val result = try {
        withTimeout(2000L) {
            delay(3000L)
            "Done"
        }
    } catch (e: TimeoutCancellationException) {
        "Timed out!"
    }

    println(result)
}