// L22 - P5: session management.
// A session keeps the user state and controls expiration.

data class SessionP5(
    val userId: String,
    val createdAtMinutes: Int,
    val ttlMinutes: Int
)

class SessionManagerSimulatorP5 {
    private var session: SessionP5? = null

    fun startSession(userId: String, nowMinutes: Int, ttlMinutes: Int = 30) {
        session = SessionP5(userId = userId, createdAtMinutes = nowMinutes, ttlMinutes = ttlMinutes)
    }

    fun isSessionValid(nowMinutes: Int): Boolean {
        val currentSession = session ?: return false
        return nowMinutes - currentSession.createdAtMinutes < currentSession.ttlMinutes
    }

    fun endSession() {
        session = null
    }
}

// Basic use case: we start a session and check its validity.
fun demoL22P5SessionManagement(): List<Boolean> {
    val manager = SessionManagerSimulatorP5()
    manager.startSession("user-1", nowMinutes = 10)
    val validNow = manager.isSessionValid(nowMinutes = 20)
    val validLater = manager.isSessionValid(nowMinutes = 50)
    return listOf(validNow, validLater)
}

fun main() {
    println("=== Session Management ===")
    val results = demoL22P5SessionManagement()
    println("Sessione valida adesso: ${results[0]}")
    println("Session valid after: ${results[1]}")
}