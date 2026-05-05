// L16 - P5: politica di backup for SharedPreferences.
// The goal is to decide which data can be included in automatic backup.

data class PreferenceEntryP5(
    val key: String,
    val value: String,
    val allowBackup: Boolean
)

class BackupPolicySimulatorP5 {
    // If allowBackup is false, the data is excluded from backup.
    fun buildBackupPayload(entries: List<PreferenceEntryP5>): Map<String, String> {
        return entries
            .filter { entry -> entry.allowBackup }
            .associate { entry -> entry.key to entry.value }
    }
}

// Basic use case: we prepare a backup that excludes sensitive data.
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
    println("Data in backup:")
    backup.forEach { (key, value) -> println("  $key: $value") }
    println("\nNota: 'token' is excluded from backup (allowBackup=false)")
}