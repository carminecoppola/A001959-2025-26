// L16 - P4: write and read di a file interno.
// In Android this file would be saved in the app's internal storage.

import java.io.File

class InternalFileStorageSimulatorP4(private val baseDirectory: File) {
    // Scriviamo the contenuto in a file interno semplice.
    fun writeFile(fileName: String, content: String) {
        val targetFile = File(baseDirectory, fileName)
        targetFile.writeText(content)
    }

    // Leggiamo the contenuto of the file se esiste.
    fun readFile(fileName: String): String {
        val targetFile = File(baseDirectory, fileName)
        return if (targetFile.exists()) targetFile.readText() else ""
    }
}

// Basic use case: we write and we read a text in a file interno.
fun demoL16P4InternalFileWrite(baseDirectory: File): String {
    val storage = InternalFileStorageSimulatorP4(baseDirectory)
    storage.writeFile("notess.txt", "Prima nota salvata nel file interno")
    return storage.readFile("notess.txt")
}

fun main() {
    println("=== Internal File Write ===")
    val tempDir = File(System.getProperty("java.io.tmpdir"), "L16_test")
    tempDir.mkdirs()
    val result = demoL16P4InternalFileWrite(tempDir)
    println(result)
    println("File salvato in: ${tempDir.absolutePath}")
}