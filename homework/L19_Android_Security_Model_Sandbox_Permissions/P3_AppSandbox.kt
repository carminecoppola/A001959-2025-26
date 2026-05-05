// L19 - P3: sandbox dell'app.
// The sandbox isolates one app's data from other apps' data.

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

// Basic use case: we save a file and verifichiamo l'isolamento.
fun demoL19P3AppSandbox(): List<String> {
    val sandbox = AppSandboxSimulatorP3(
        SandboxFolderP3(appId = "com.example.app", files = mutableListOf())
    )

    return listOf(
        sandbox.saveSensitiveData("profile.db"),
        "Can another app read it? ${sandbox.canReadFromOtherApp("com.other.app")}",
        "File nella sandbox: ${sandbox.listFiles().joinToString()}"
    )
}

fun main() {
    println("=== App Sandbox ===")
    val results = demoL19P3AppSandbox()
    results.forEach { println(it) }
}