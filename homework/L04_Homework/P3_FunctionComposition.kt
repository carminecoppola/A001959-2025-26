/*
Problem 3 - Function Composition

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_FunctionComposition.kt -include-runtime -d P3_FunctionComposition.jar
2. Run the program:
   java -jar P3_FunctionComposition.jar
*/

fun compose(f: (Int)->Int, g: (Int)->Int): (Int)->Int = { x -> f(g(x)) }

fun main() {
    val double = { n: Int -> n * 2 }
    val addOne = { n: Int -> n + 1 }
    val doubleAddOne = compose(addOne, double)
    println(doubleAddOne(5))  // double(5)=10, then addOne(10)=11
}