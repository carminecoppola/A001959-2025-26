/*
Problem 1 - Function Overloading with Defaults

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_FunctionOverloadingWithDefaults.kt -include-runtime -d P1_FunctionOverloadingWithDefaults.jar
2. Run the program:
   java -jar P1_FunctionOverloadingWithDefaults.jar
*/

fun greetUser(name: String, language: String = "en", formal: Boolean = false): String {
    return when (language) {
        "it" -> if (formal) "Good morning, $name" else "Hi, $name!"
        else -> if (formal) "Good morning, $name" else "Hey $name!"
    }
}

fun main() {
    println(greetUser("Alice"))
    println(greetUser("Bob", formal = true))
    println(greetUser("Carlo", "it", formal = true))
}