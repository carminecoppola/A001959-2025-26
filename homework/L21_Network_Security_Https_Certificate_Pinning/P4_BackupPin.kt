// L21 - P4: pin di backup.
// Un backup pin e un secondo valore di fiducia se il pin principale cambia.

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

// Caso d'uso di base: testiamo il pin principale, il backup e un valore errato.
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