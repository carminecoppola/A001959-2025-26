// L19 - P5: rischio legato ad allowBackup.
// Se allowBackup and attivo senza attenzione, sensitive data possono finire nel backup.

data class BackupRiskP5(
    val allowBackup: Boolean,
    val containsSensitiveData: Boolean
)

class AllowBackupRiskAnalyzerP5 {
    fun evaluate(risk: BackupRiskP5): String {
        return when {
            !risk.allowBackup -> "Low risk: backup is disabled"
            risk.containsSensitiveData -> "Rischio alto: backup attivo with sensitive data"
            else -> "Rischio moderato: backup attivo ma senza sensitive data"
        }
    }
}

// Basic use case: valutiamo three scenari different.
fun demoL19P5AllowBackupRisk(): List<String> {
    val analyzer = AllowBackupRiskAnalyzerP5()
    return listOf(
        analyzer.evaluate(BackupRiskP5(allowBackup = false, containsSensitiveData = true)),
        analyzer.evaluate(BackupRiskP5(allowBackup = true, containsSensitiveData = true)),
        analyzer.evaluate(BackupRiskP5(allowBackup = true, containsSensitiveData = false))
    )
}

fun main() {
    println("=== AllowBackup Risk ===")
    val results = demoL19P5AllowBackupRisk()
    results.forEach { println(it) }
}