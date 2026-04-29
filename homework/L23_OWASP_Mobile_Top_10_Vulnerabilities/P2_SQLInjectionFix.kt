// L23 - P2: correzione di una vulnerabilita SQL injection.
// Questo file mostra il passaggio da una query insicura a una sicura.

class SQLInjectionFixSimulatorP2 {
    fun unsafeQuery(userInput: String): String {
        return "SELECT * FROM products WHERE name = '$userInput'"
    }

    fun safeQuery(userInput: String): QueryP1 {
        return QueryP1(
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