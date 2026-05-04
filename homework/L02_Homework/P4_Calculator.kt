/*
Problem 4 - Calculator

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_Calculator.kt -include-runtime -d P4_Calculator.jar
2. Run the program:
   java -jar P4_Calculator.jar
*/

fun main() {
    print("Num1: "); val a = readLine()!!.toDouble()
    print("Op (+,-,*,/): "); val op = readLine()!!
    print("Num2: "); val b = readLine()!!.toDouble()
    val result = when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else null
        else -> null
    }
    println(if (result != null) "= $result" else "Error")
}
