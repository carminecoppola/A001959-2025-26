// L19 - P3: sandbox dell'app.
// La sandbox isola i dati di un'app da quelli delle altre app.

data class SandboxFolderP3(
    val appId: String,
    val files: MutableList<String>
)

class AppSandboxSimulatorP3(
    private val folder: SandboxFolderP3
) {
    fun saveSensitiveData(fileName: String): String {
        folder.files.add(fileName)
        return "Dato salvato nella sandbox di ${folder.appId}: $fileName"
    }

    fun listFiles(): List<String> {
        return folder.files.toList()
    }

    fun canReadFromOtherApp(otherAppId: String): Boolean {
        return otherAppId == folder.appId
    }
}

// Caso d'uso di base: salviamo un file e verifichiamo l'isolamento.
fun demoL19P3AppSandbox(): List<String> {
    val sandbox = AppSandboxSimulatorP3(
        SandboxFolderP3(appId = "com.example.app", files = mutableListOf())
    )

    return listOf(
        sandbox.saveSensitiveData("profile.db"),
        "Può un'altra app leggere? ${sandbox.canReadFromOtherApp("com.other.app")}",
        "File nella sandbox: ${sandbox.listFiles().joinToString()}"
    )
}