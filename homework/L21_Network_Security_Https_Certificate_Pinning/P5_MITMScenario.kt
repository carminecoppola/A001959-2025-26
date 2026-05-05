// L21 - P5: scenario Man-in-the-Middle (MITM).
// We simulate a check that blocks unexpected certificates.

class MITMDetectionSimulatorP5(
    private val trustedPins: Set<String>
) {
    fun inspect(receivedPin: String): String {
        return if (receivedPin in trustedPins) {
            "Secure connection: recognized pin"
        } else {
            "Possible MITM: unrecognized pin"
        }
    }
}

// Basic use case: we check a valid connection and a suspicious one.
fun demoL21P5MITMScenario(): List<String> {
    val detector = MITMDetectionSimulatorP5(
        trustedPins = setOf("pin-main", "pin-backup")
    )

    return listOf(
        detector.inspect("pin-main"),
        detector.inspect("unknown-pin")
    )
}

fun main() {
    println("=== MITM Scenario ===")
    val results = demoL21P5MITMScenario()
    results.forEach { println(it) }
}