/*
Problem 3 - Number Pattern

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_NumberPattern.kt -include-runtime -d P3_NumberPattern.jar
2. Run the program:
   java -jar P3_NumberPattern.jar
*/

fun main() {
    print("Rows: ")
    val rows = readLine()!!.toInt()
    for (row in 1..rows) {
        for (star in 1..row) print("* ")
        println()
    }
}
