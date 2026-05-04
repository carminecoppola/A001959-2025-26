/*
Problem 5 - Password Generator

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_PasswordGenerator.kt -include-runtime -d P5_PasswordGenerator.jar
2. Run the program:
   java -jar P5_PasswordGenerator.jar
*/

fun generatePassword(
    length: Int,
    useUpper: Boolean = true,
    useDigits: Boolean = true,
    useSymbols: Boolean = false
): String {
    var pool = "abcdefghijklmnopqrstuvwxyz"
    if (useUpper)   pool += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (useDigits)  pool += "0123456789"
    if (useSymbols) pool += "!@#$%^&*"

    return (1..length).map { pool.random() }.joinToString("")
}

fun main() {
    println(generatePassword(16, useSymbols = true))
}