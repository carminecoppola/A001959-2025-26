// L20 - P2: encryption dei data usando the Keystore.
// Data is transformed before being stored and then recovered in plaintext.

class DataEncryptionSimulatorP2 {
    private fun encrypt(value: String): String = value.reversed()

    private fun decrypt(value: String): String = value.reversed()

    fun encryptData(plainText: String): String {
        return encrypt(plainText)
    }

    fun decryptData(cipherText: String): String {
        return decrypt(cipherText)
    }
}

// Basic use case: we encrypt a text and then recover it.
fun demoL20P2DataEncryption(): List<String> {
    val encryption = DataEncryptionSimulatorP2()
    val cipher = encryption.encryptData("messaggio segreto")
    val plain = encryption.decryptData(cipher)
    return listOf(cipher, plain)
}

fun main() {
    println("=== Data Encryption ===")
    val results = demoL20P2DataEncryption()
    println("Ciphertext: ${results[0]}")
    println("Plaintext: ${results[1]}")
}