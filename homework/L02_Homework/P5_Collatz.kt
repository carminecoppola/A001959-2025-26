/*
Problem 5 - Collatz Conjecture

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_Collatz.kt -include-runtime -d P5_Collatz.jar
2. Run the program:
   java -jar P5_Collatz.jar
*/

fun main() {
    print("n: ")
    var n = readLine()!!.toLong()
    var steps = 0
    while (n != 1L) {
        n = if (n % 2 == 0L) n / 2 else n * 3 + 1
        steps++
    }
    println("Reached 1 in $steps steps")
}
