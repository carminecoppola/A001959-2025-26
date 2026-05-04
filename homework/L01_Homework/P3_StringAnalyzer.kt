/*
Problem 3 - String Analyzer

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_StringAnalyzer.kt -include-runtime -d P3_StringAnalyzer.jar
2. Run the program:
   java -jar P3_StringAnalyzer.jar
*/
fun main() {
    print("Enter a string: ")
    val s = readLine() ?: ""
    println("Length: ${s.length}")
    println("Uppercase: ${s.uppercase()}")
    println("Contains 'kotlin': ${s.lowercase().contains("kotlin")}")
    if (s.isNotEmpty()) {
        println("First: ${s.first()}, Last: ${s.last()}")
    }
}
