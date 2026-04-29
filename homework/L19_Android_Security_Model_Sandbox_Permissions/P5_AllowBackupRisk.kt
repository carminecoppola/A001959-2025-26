// L19 - P5: rischio legato ad allowBackup.
// Se allowBackup e attivo senza attenzione, dati sensibili possono finire nel backup.

data class BackupRiskP5(
    val allowBackup: Boolean,
    val containsSensitiveData: Boolean
)

class AllowBackupRiskAnalyzerP5 {
    fun evaluate(risk: BackupRiskP5): String {
        return when {
            !risk.allowBackup -> "Rischio basso: il backup e disabilitato"
            risk.containsSensitiveData -> "Rischio alto: backup attivo con dati sensibili"
            else -> "Rischio moderato: backup attivo ma senza dati sensibili"
        }
    }
}

// Caso d'uso di base: valutiamo tre scenari diversi.
fun demoL19P5AllowBackupRisk(): List<String> {
    val analyzer = AllowBackupRiskAnalyzerP5()
    return listOf(
        analyzer.evaluate(BackupRiskP5(allowBackup = false, containsSensitiveData = true)),
        analyzer.evaluate(BackupRiskP5(allowBackup = true, containsSensitiveData = true)),
        analyzer.evaluate(BackupRiskP5(allowBackup = true, containsSensitiveData = false))
    )
}