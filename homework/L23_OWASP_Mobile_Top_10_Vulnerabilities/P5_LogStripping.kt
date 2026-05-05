// L23 - P5: rimozione dei log sensibili.
// In produzione i log devono avoid di stampare segreti o data privati.

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

// Basic use case: we show the log in debug and in release.
fun demoL23P5LogStripping(): List<String> {
    val simulator = LogStrippingSimulatorP5()
    return listOf(
        simulator.log(BuildTypeP5.DEBUG, "Token: secret-123"),
        simulator.log(BuildTypeP5.RELEASE, "Token: secret-123")
    )
}

fun main() {
    println("=== Log Stripping ===")
    val results = demoL23P5LogStripping()
    println("Debug: ${results[0]}")
    println("Release: ${results[1]}")
}