// L23 - P2: correzione di a vulnerabilita SQL injection.
// This file shows the transition from an insecure query to a secure one.

data class QueryP2(
    val sql: String,
    val parameters: List<String>
)

class SQLInjectionFixSimulatorP2 {
    fun unsafeQuery(userInput: String): String {
        return "SELECT * FROM products WHERE name = '$userInput'"
    }

    fun safeQuery(userInput: String): QueryP2 {
        return QueryP2(
            sql = "SELECT * FROM products WHERE name = ?",
            parameters = listOf(userInput)
        )
    }
}

// Basic use case: we compare an insecure query and a secure query.
fun demoL23P2SQLInjectionFix(): List<String> {
    val simulator = SQLInjectionFixSimulatorP2()
    return listOf(
        simulator.unsafeQuery("' OR '1'='1"),
        simulator.safeQuery("' OR '1'='1").sql
    )
}

fun main() {
    println("=== SQL Injection Fix ===")
    val results = demoL23P2SQLInjectionFix()
    println("Vulnerabile: ${results[0]}")
    println("Sicura: ${results[1]}")
}