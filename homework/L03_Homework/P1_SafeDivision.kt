/*
Problem 1 - Safe Division

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_SafeDivision.kt -include-runtime -d P1_SafeDivision.jar
2. Run the program:
   java -jar P1_SafeDivision.jar
*/

fun safeDivide(a: Int, b: Int): Int? = if (b == 0) null else a / b

fun main() {
    println(safeDivide(10, 2))  // 5
    println(safeDivide(10, 0))  // null
    val result = safeDivide(10, 0) ?: -1
    println("Result: $result")  // Result: -1
}