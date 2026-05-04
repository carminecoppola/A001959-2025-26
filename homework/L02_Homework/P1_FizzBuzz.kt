/*
Problem 1 - FizzBuzz

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_FizzBuzz.kt -include-runtime -d P1_FizzBuzz.jar
2. Run the program:
   java -jar P1_FizzBuzz.jar
*/

fun main() {
    for (i in 1..50) {
        println(when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3  == 0 -> "Fizz"
            i % 5  == 0 -> "Buzz"
            else        -> "$i"
        })
    }
}
