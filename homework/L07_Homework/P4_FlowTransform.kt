/*
Problem 4 - Flow Transform

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_FlowTransform.kt -include-runtime -d P4_FlowTransform.jar
2. Run the program:
   java -jar P4_FlowTransform.jar
*/

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    (1..10).asFlow()
        .onEach { delay(100) }
        .filter { it % 2 == 0 }
        .map { it * 2 }
        .take(3)
        .collect { println(it) }
}