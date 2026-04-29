// L20 - P3: file criptato con l'API EncryptedFile.
// L'esempio simula la scrittura di un contenuto protetto su file.

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

// Caso d'uso di base: scriviamo e leggiamo un file cifrato.
fun demoL20P3EncryptedFile(directory: File): String {
    val encryptedFile = EncryptedFileSimulatorP3(directory)
    encryptedFile.writeEncryptedFile("secure.txt", "contenuto protetto")
    return encryptedFile.readEncryptedFile("secure.txt")
}