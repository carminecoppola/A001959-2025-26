/*
Problem 5 - requireNotNull Practice

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_RequireNotNullPractice.kt -include-runtime -d P5_RequireNotNullPractice.jar
2. Run the program:
   java -jar P5_RequireNotNullPractice.jar
*/

fun validateConfig(config: Map<String, String?>) {
    val host   = requireNotNull(config["host"])   { "host is required" }
    val port   = requireNotNull(config["port"])   { "port is required" }
    val apiKey = requireNotNull(config["apiKey"]) { "apiKey is required" }

    println("Config OK: $host:$port key=$apiKey")
}

fun main() {
    validateConfig(mapOf("host" to "api.example.com", "port" to "443", "apiKey" to "secret"))
}