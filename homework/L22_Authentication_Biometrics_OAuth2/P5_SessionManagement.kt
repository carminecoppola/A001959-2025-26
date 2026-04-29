// L22 - P5: gestione della sessione.
// Una sessione conserva lo stato dell'utente e controlla la scadenza.

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

// Caso d'uso di base: avviamo una sessione e ne controlliamo la validita.
fun demoL22P5SessionManagement(): List<Boolean> {
    val manager = SessionManagerSimulatorP5()
    manager.startSession("user-1", nowMinutes = 10)
    val validNow = manager.isSessionValid(nowMinutes = 20)
    val validLater = manager.isSessionValid(nowMinutes = 50)
    return listOf(validNow, validLater)
}