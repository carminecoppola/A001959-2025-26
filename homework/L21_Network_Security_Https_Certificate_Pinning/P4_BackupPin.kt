// L21 - P4: pin di backup.
// Un backup pin and a second valore di fiducia se the pin principale cambia.

class CertificatePinningSimulatorP4(
    private val primaryPin: String,
    private val backupPin: String
) {
    fun isPinValid(receivedPin: String): Boolean {
        return receivedPin == primaryPin || receivedPin == backupPin
    }

    fun describe(receivedPin: String): String {
        return if (isPinValid(receivedPin)) {
            "Pin accettato"
        } else {
            "Pin rifiutato"
        }
    }
}

// Basic use case: testiamo the pin principale, the backup and a valore errato.
fun demoL21P4BackupPin(): List<String> {
    val pinning = CertificatePinningSimulatorP4(
        primaryPin = "pin-main",
        backupPin = "pin-backup"
    )

    return listOf(
        pinning.describe("pin-main"),
        pinning.describe("pin-backup"),
        pinning.describe("pin-evil")
    )
}

fun main() {
    println("=== Backup Pin ===")
    val results = demoL21P4BackupPin()
    results.forEach { println(it) }
}