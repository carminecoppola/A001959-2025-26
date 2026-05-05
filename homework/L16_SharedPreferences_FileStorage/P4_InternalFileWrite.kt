// L16 - P4: writing and reading an internal file.
// In Android this file would be saved in the app's internal storage.

import java.io.File

class InternalFileStorageSimulatorP4(private val baseDirectory: File) {
    // We write content to a simple internal file.
    fun writeFile(fileName: String, content: String) {
        val targetFile = File(baseDirectory, fileName)
        targetFile.writeText(content)
    }

    // We read file content if it exists.
    fun readFile(fileName: String): String {
        val targetFile = File(baseDirectory, fileName)
        return if (targetFile.exists()) targetFile.readText() else ""
    }
}

// Basic use case: we write and read text in an internal file.
fun demoL16P4InternalFileWrite(baseDirectory: File): String {
    val storage = InternalFileStorageSimulatorP4(baseDirectory)
    storage.writeFile("notess.txt", "First note saved in internal file")
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