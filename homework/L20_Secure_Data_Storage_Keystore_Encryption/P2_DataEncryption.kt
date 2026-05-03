// L20 - P2: cifratura dei dati usando il Keystore.
// Il dato viene trasformato prima di essere salvato e poi recuperato in chiaro.

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

// Caso d'uso di base: cifriamo un testo e poi lo recuperiamo.
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