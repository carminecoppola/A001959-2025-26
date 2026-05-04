/*
Problem 2 - Higher-Order Calculator

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_HigherOrderCalculator.kt -include-runtime -d P2_HigherOrderCalculator.jar
2. Run the program:
   java -jar P2_HigherOrderCalculator.jar
*/

fun calculate(a: Double, b: Double, op: (Double, Double) -> Double) = op(a, b)

fun main() {
    val add = { x: Double, y: Double -> x + y }
    val sub = { x: Double, y: Double -> x - y }
    val mul = { x: Double, y: Double -> x * y }
    val div = { x: Double, y: Double -> if (y != 0.0) x / y else Double.NaN }

    println(calculate(10.0, 3.0, add))
    println(calculate(10.0, 3.0, mul))
    println(calculate(10.0, 3.0, sub))
    println(calculate(10.0, 3.0, div))


}