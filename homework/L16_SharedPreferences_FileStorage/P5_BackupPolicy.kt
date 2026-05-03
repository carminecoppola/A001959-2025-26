// L16 - P5: politica di backup per SharedPreferences.
// L'obiettivo e decidere quali dati possono essere inclusi nel backup automatico.

data class PreferenceEntryP5(
    val key: String,
    val value: String,
    val allowBackup: Boolean
)

class BackupPolicySimulatorP5 {
    // Se allowBackup e false, il dato viene escluso dal backup.
    fun buildBackupPayload(entries: List<PreferenceEntryP5>): Map<String, String> {
        return entries
            .filter { entry -> entry.allowBackup }
            .associate { entry -> entry.key to entry.value }
    }
}

// Caso d'uso di base: prepariamo un backup che esclude i dati sensibili.
fun demoL16P5BackupPolicy(): Map<String, String> {
    val entries = listOf(
        PreferenceEntryP5("theme", "dark", allowBackup = true),
        PreferenceEntryP5("token", "secret-token", allowBackup = false),
        PreferenceEntryP5("language", "it", allowBackup = true)
    )

    return BackupPolicySimulatorP5().buildBackupPayload(entries)
}

fun main() {
    println("=== Backup Policy ===")
    val backup = demoL16P5BackupPolicy()
    println("Dati nel backup:")
    backup.forEach { (key, value) -> println("  $key: $value") }
    println("\nNota: 'token' è escluso dal backup (allowBackup=false)")
}