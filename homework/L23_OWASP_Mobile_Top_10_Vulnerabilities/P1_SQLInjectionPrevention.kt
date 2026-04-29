// L23 - P1: prevenzione SQL injection.
// L'obiettivo e evitare query costruite concatenando testo non fidato.

data class QueryP1(
    val sql: String,
    val parameters: List<String>
)

class SQLInjectionPreventionSimulatorP1 {
    fun buildSafeQuery(userId: String): QueryP1 {
        return QueryP1(
            sql = "SELECT * FROM users WHERE id = ?",
            parameters = listOf(userId)
        )
    }
}

// Caso d'uso di base: costruiamo una query sicura con parametro separato.
fun demoL23P1SQLInjectionPrevention(): QueryP1 {
    return SQLInjectionPreventionSimulatorP1().buildSafeQuery("42")
}