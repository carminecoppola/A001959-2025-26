// L16 - P2: EncryptedSharedPreferences.
// Qui simuliamo il concetto di dato salvato in forma protetta prima di essere letto.

class EncryptedSharedPreferencesSimulatorP2 {
    private val storage = mutableMapOf<String, String>()

    // Usiamo una trasformazione molto semplice per mostrare l'idea di cifratura.
    // In una vera app Android si userebbe la libreria EncryptedSharedPreferences.
    private fun encrypt(value: String): String = value.reversed()

    private fun decrypt(value: String): String = value.reversed()

    fun putSecureString(key: String, value: String) {
        storage[key] = encrypt(value)
    }

    fun getSecureString(key: String, defaultValue: String = ""): String {
        val encryptedValue = storage[key] ?: return defaultValue
        return decrypt(encryptedValue)
    }
}

// Caso d'uso di base: salviamo e leggiamo un token in modo protetto.
fun demoL16P2EncryptedSharedPreferences(): String {
    val prefs = EncryptedSharedPreferencesSimulatorP2()
    prefs.putSecureString("token", "abc123")
    return prefs.getSecureString("token")
}

fun main() {
    println("=== EncryptedSharedPreferences ===")
    println("Token salvato: ${demoL16P2EncryptedSharedPreferences()}")
}