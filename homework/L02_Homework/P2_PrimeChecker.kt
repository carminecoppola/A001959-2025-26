/*
Problem 2 - Prime Checker

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_PrimeChecker.kt -include-runtime -d P2_PrimeChecker.jar
2. Run the program:
   java -jar P2_PrimeChecker.jar
*/

fun isPrime(n: Int): Boolean {
    // Base case: there are no prime numbers below 2.
    if (n < 2) return false

    // Verifica i divisori fino alla radice quadrata, che è sufficiente per sapere se n è composto.
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

fun main() {
    print("Number: ")
    val n = readLine()!!.toInt()
    println(if (isPrime(n)) "$n is prime" else "$n is NOT prime")
}
