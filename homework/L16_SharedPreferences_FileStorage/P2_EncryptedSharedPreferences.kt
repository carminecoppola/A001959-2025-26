// L16 - P2: EncryptedSharedPreferences.
// Here we simulate the concept of data saved in protected form before being read.

class EncryptedSharedPreferencesSimulatorP2 {
    private val storage = mutableMapOf<String, String>()

    // Usiamo a trasformazione molto semplice for showsre l'idea di encryption.
    // In a vera app Android si userebbe the libreria EncryptedSharedPreferences.
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

// Basic use case: we save and we read a token in modo protetto.
fun demoL16P2EncryptedSharedPreferences(): String {
    val prefs = EncryptedSharedPreferencesSimulatorP2()
    prefs.putSecureString("token", "abc123")
    return prefs.getSecureString("token")
}

fun main() {
    println("=== EncryptedSharedPreferences ===")
    println("Token salvato: ${demoL16P2EncryptedSharedPreferences()}")
}