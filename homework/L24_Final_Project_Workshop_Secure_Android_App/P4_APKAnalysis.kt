// L24 - P4: analisi dell'APK.
// L'obiettivo e individuare segnali semplici di configurazione o sicurezza da controllare.

data class ApkFindingP4(
    val severity: String,
    val message: String
)

class ApkAnalysisSimulatorP4 {
    fun analyze(fileName: String, usesCleartext: Boolean, exportedCount: Int): List<ApkFindingP4> {
        val findings = mutableListOf<ApkFindingP4>()

        if (usesCleartext) {
            findings.add(ApkFindingP4("high", "L'APK usa traffico in chiaro"))
        }

        if (exportedCount > 2) {
            findings.add(ApkFindingP4("medium", "Troppi componenti esportati"))
        }

        if (findings.isEmpty()) {
            findings.add(ApkFindingP4("info", "Nessun problema evidente trovato in $fileName"))
        }

        return findings
    }
}

// Caso d'uso di base: analizziamo un APK simulato.
fun demoL24P4APKAnalysis(): List<ApkFindingP4> {
    return ApkAnalysisSimulatorP4().analyze(
        fileName = "app-release.apk",
        usesCleartext = true,
        exportedCount = 4
    )
}