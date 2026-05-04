/*
Problem 5 - Set Operations

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_SetOperations.kt -include-runtime -d P5_SetOperations.jar
2. Run the program:
   java -jar P5_SetOperations.jar
*/

fun main() {
    val classA = setOf(1, 2, 3, 4, 5)
    val classB = setOf(3, 4, 5, 6, 7)

    println("Both: ${classA.intersect(classB)}")
    println("Only A: ${classA.subtract(classB)}")
    println("Only B: ${classB.subtract(classA)}")
    println("Either: ${classA.union(classB)}")
}
