// L22 - P2: memorizzazione sicura dei token.
// L'idea e proteggere il token prima di salvarlo localmente.

class SecureTokenStorageSimulatorP2 {
    private var encryptedAccessToken: String? = null
    private var encryptedRefreshToken: String? = null

    private fun encrypt(value: String): String = value.reversed()
    private fun decrypt(value: String): String = value.reversed()

    fun saveAccessToken(token: String) {
        encryptedAccessToken = encrypt(token)
    }

    fun saveRefreshToken(token: String) {
        encryptedRefreshToken = encrypt(token)
    }

    fun readAccessToken(): String? {
        val storedValue = encryptedAccessToken ?: return null
        return decrypt(storedValue)
    }

    fun readRefreshToken(): String? {
        val storedValue = encryptedRefreshToken ?: return null
        return decrypt(storedValue)
    }

    fun clearToken() {
        encryptedAccessToken = null
        encryptedRefreshToken = null
    }
}

// Caso d'uso di base: salviamo, leggiamo e poi cancelliamo un token.
fun demoL22P2TokenStorage(): List<String?> {
    val storage = SecureTokenStorageSimulatorP2()
    storage.saveAccessToken("access-token-123")
    storage.saveRefreshToken("refresh-token-456")
    val savedAccess = storage.readAccessToken()
    val savedRefresh = storage.readRefreshToken()
    storage.clearToken()
    val cleared = storage.readAccessToken()
    return listOf(savedAccess, savedRefresh, cleared)
}

fun main() {
    println("=== Token Storage ===")
    val results = demoL22P2TokenStorage()
    println("Access token: ${results[0]}")
    println("Refresh token: ${results[1]}")
    println("Dopo clear: ${results[2]}")
}