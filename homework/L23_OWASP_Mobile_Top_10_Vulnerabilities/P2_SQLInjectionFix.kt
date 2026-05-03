// L23 - P2: correzione di una vulnerabilita SQL injection.
// Questo file mostra il passaggio da una query insicura a una sicura.

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

// Caso d'uso di base: confrontiamo query insicura e query sicura.
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