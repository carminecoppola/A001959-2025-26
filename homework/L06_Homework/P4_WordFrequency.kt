/*
Problem 4 - Word Frequency

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_WordFrequency.kt -include-runtime -d P4_WordFrequency.jar
2. Run the program:
   java -jar P4_WordFrequency.jar
*/

fun main() {
    print("Sentence: ")
    val words = readLine()!!.lowercase().split(" ")

    words.groupingBy { it }
        .eachCount()
        .entries
        .sortedByDescending { it.value }
        .forEach { (word, count) -> println("$word: $count") }
}
