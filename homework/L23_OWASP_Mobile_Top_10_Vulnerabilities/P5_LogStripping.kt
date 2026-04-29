// L23 - P5: rimozione dei log sensibili.
// In produzione i log devono evitare di stampare segreti o dati privati.

enum class BuildTypeP5 {
    DEBUG,
    RELEASE
}

class LogStrippingSimulatorP5 {
    fun log(buildType: BuildTypeP5, message: String): String {
        return if (buildType == BuildTypeP5.DEBUG) {
            message
        } else {
            "[REDACTED]"
        }
    }
}

// Caso d'uso di base: mostriamo il log in debug e in release.
fun demoL23P5LogStripping(): List<String> {
    val simulator = LogStrippingSimulatorP5()
    return listOf(
        simulator.log(BuildTypeP5.DEBUG, "Token: secret-123"),
        simulator.log(BuildTypeP5.RELEASE, "Token: secret-123")
    )
}