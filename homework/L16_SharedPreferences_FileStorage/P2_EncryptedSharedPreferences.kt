// L16 - P2: EncryptedSharedPreferences.
// Here we simulate the concept of data saved in protected form before being read.

class EncryptedSharedPreferencesSimulatorP2 {
    private val storage = mutableMapOf<String, String>()

    // We use a very simple transformation to show the idea of encryption.
    // In a real Android app, you would use the EncryptedSharedPreferences library.
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

// Basic use case: we save and read a token in protected form.
fun demoL16P2EncryptedSharedPreferences(): String {
    val prefs = EncryptedSharedPreferencesSimulatorP2()
    prefs.putSecureString("token", "abc123")
    return prefs.getSecureString("token")
}

fun main() {
    println("=== EncryptedSharedPreferences ===")
    println("Token salvato: ${demoL16P2EncryptedSharedPreferences()}")
}