// L16 - P4: scrittura e lettura di un file interno.
// In Android questo file sarebbe salvato nella memoria interna dell'app.

import java.io.File

class InternalFileStorageSimulatorP4(private val baseDirectory: File) {
    // Scriviamo il contenuto in un file interno semplice.
    fun writeFile(fileName: String, content: String) {
        val targetFile = File(baseDirectory, fileName)
        targetFile.writeText(content)
    }

    // Leggiamo il contenuto del file se esiste.
    fun readFile(fileName: String): String {
        val targetFile = File(baseDirectory, fileName)
        return if (targetFile.exists()) targetFile.readText() else ""
    }
}

// Caso d'uso di base: scriviamo e leggiamo un testo in un file interno.
fun demoL16P4InternalFileWrite(baseDirectory: File): String {
    val storage = InternalFileStorageSimulatorP4(baseDirectory)
    storage.writeFile("notes.txt", "Prima nota salvata nel file interno")
    return storage.readFile("notes.txt")
}