/*
Problem 5 - Password Validator

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_PasswordValidator.kt -include-runtime -d P5_PasswordValidator.jar
2. Run the program:
   java -jar P5_PasswordValidator.jar
*/
fun main() {
    print("Password: ")
    val pwd = readLine() ?: ""
    val long  = pwd.length >= 8
    val digit = pwd.any { it.isDigit() }
    val upper = pwd.any { it.isUpperCase() }
    println("At least 8 chars: $long")
    println("Contains digit:   $digit")
    println("Contains upper:   $upper")
    println(if (long && digit && upper) "VALID" else "INVALID")
}
