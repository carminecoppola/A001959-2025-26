// L22 - P2: memorizzazione sicura dei token.
// L'idea e proteggere il token prima di salvarlo localmente.

class SecureTokenStorageSimulatorP2 {
    private var encryptedToken: String? = null

    private fun encrypt(value: String): String = value.reversed()
    private fun decrypt(value: String): String = value.reversed()

    fun saveToken(token: String) {
        encryptedToken = encrypt(token)
    }

    fun readToken(): String? {
        val storedValue = encryptedToken ?: return null
        return decrypt(storedValue)
    }

    fun clearToken() {
        encryptedToken = null
    }
}

// Caso d'uso di base: salviamo, leggiamo e poi cancelliamo un token.
fun demoL22P2TokenStorage(): List<String?> {
    val storage = SecureTokenStorageSimulatorP2()
    storage.saveToken("access-token-123")
    val saved = storage.readToken()
    storage.clearToken()
    val cleared = storage.readToken()
    return listOf(saved, cleared)
}