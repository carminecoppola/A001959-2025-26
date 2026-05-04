/*
Problem 4 - List Statistics

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_ListStatistics.kt -include-runtime -d P4_ListStatistics.jar
2. Run the program:
   java -jar P4_ListStatistics.jar
*/

fun main() {
    val nums = listOf(3, 7, 2, 9, 4, 6, 1, 8, 5)

    println("Sum: ${nums.sum()}")
    println("Avg: ${nums.average()}")
    println("Max: ${nums.maxOrNull()}")
    println("Min: ${nums.minOrNull()}")
    println("Even count: ${nums.count { it % 2 == 0 }}")
}