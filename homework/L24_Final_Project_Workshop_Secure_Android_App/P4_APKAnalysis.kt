// L24 - P4: analisi dell'APK.
// L'obiettivo e individuare segnali semplici di configurazione o sicurezza da controllare.

data class ApkFindingP4(
    val severity: String,
    val message: String
)

data class ApkManifestSnapshotP4(
    val usesCleartext: Boolean,
    val debuggable: Boolean,
    val exportedCount: Int,
    val permissions: Set<String>,
    val hardcodedSecretsInResources: Boolean
)

class ApkAnalysisSimulatorP4 {
    fun parseManifest(manifestText: String, resourceText: String): ApkManifestSnapshotP4 {
        val usesCleartext = Regex("usesCleartextTraffic=\\\"true\\\"").containsMatchIn(manifestText)
        val debuggable = Regex("debuggable=\\\"true\\\"").containsMatchIn(manifestText)
        val exportedCount = Regex("exported=\\\"true\\\"").findAll(manifestText).count()
        val permissions = Regex("uses-permission android:name=\\\"([^\\\"]+)\\\"")
            .findAll(manifestText)
            .map { it.groupValues[1] }
            .toSet()
        val hardcodedSecrets = Regex("(api_key|secret|token)", RegexOption.IGNORE_CASE).containsMatchIn(resourceText)

        return ApkManifestSnapshotP4(
            usesCleartext = usesCleartext,
            debuggable = debuggable,
            exportedCount = exportedCount,
            permissions = permissions,
            hardcodedSecretsInResources = hardcodedSecrets
        )
    }

    fun analyze(fileName: String, snapshot: ApkManifestSnapshotP4): List<ApkFindingP4> {
        val findings = mutableListOf<ApkFindingP4>()
        val highRiskPermissions = setOf("android.permission.READ_SMS", "android.permission.RECORD_AUDIO")

        if (snapshot.usesCleartext) {
            findings.add(ApkFindingP4("high", "L'APK usa traffico in chiaro"))
        }

        if (snapshot.exportedCount > 2) {
            findings.add(ApkFindingP4("medium", "Troppi componenti esportati"))
        }

        if (snapshot.debuggable) {
            findings.add(ApkFindingP4("high", "Build debuggable abilitata in release"))
        }

        if (snapshot.hardcodedSecretsInResources) {
            findings.add(ApkFindingP4("high", "Possibili secret hardcoded trovati"))
        }

        val dangerousPermissions = snapshot.permissions.intersect(highRiskPermissions)
        if (dangerousPermissions.isNotEmpty()) {
            findings.add(ApkFindingP4("medium", "Permessi sensibili da giustificare: ${dangerousPermissions.joinToString()}"))
        }

        if (findings.isEmpty()) {
            findings.add(ApkFindingP4("info", "Nessun problema evidente trovato in $fileName"))
        }

        return findings
    }
}

// Caso d'uso di base: analizziamo un APK simulato.
fun demoL24P4APKAnalysis(): List<ApkFindingP4> {
    val manifest = """
        <application usesCleartextTraffic="true" debuggable="true">
            <activity android:name=".MainActivity" exported="true" />
            <activity android:name=".SettingsActivity" exported="true" />
            <receiver android:name=".BootReceiver" exported="true" />
            <uses-permission android:name="android.permission.INTERNET" />
            <uses-permission android:name="android.permission.READ_SMS" />
        </application>
    """.trimIndent()

    val resources = "api_key=ABC123"
    val simulator = ApkAnalysisSimulatorP4()
    val snapshot = simulator.parseManifest(manifestText = manifest, resourceText = resources)
    return simulator.analyze(fileName = "app-release.apk", snapshot = snapshot)
}

fun main() {
    println("=== APK Analysis ===")
    val findings = demoL24P4APKAnalysis()
    findings.forEach { println("${it.severity.uppercase()}: ${it.message}") }
}