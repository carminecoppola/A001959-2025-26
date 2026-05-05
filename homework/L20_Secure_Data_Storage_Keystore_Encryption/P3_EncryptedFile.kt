// L20 - P3: file encrypted with l'API EncryptedFile.
// The example simulates writing protected content to file.

import java.io.File

class EncryptedFileSimulatorP3(private val directory: File) {
    private fun encrypt(value: String): String = value.reversed()
    private fun decrypt(value: String): String = value.reversed()

    fun writeEncryptedFile(fileName: String, content: String) {
        File(directory, fileName).writeText(encrypt(content))
    }

    fun readEncryptedFile(fileName: String): String {
        val file = File(directory, fileName)
        return if (file.exists()) decrypt(file.readText()) else ""
    }
}

// Basic use case: we write and we read a file encrypted.
fun demoL20P3EncryptedFile(directory: File): String {
    val encryptedFile = EncryptedFileSimulatorP3(directory)
    encryptedFile.writeEncryptedFile("secure.txt", "protected content")
    return encryptedFile.readEncryptedFile("secure.txt")
}

fun main() {
    println("=== Encrypted File ===")
    val tempDir = File(System.getProperty("java.io.tmpdir"), "L20_encrypted")
    tempDir.mkdirs()
    val result = demoL20P3EncryptedFile(tempDir)
    println("Contenuto deencrypted: $result")
}