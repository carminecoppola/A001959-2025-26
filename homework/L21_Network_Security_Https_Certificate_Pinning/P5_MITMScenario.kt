// L21 - P5: scenario Man-in-the-Middle (MITM).
// Simuliamo un controllo che blocca certificati non attesi.

class MITMDetectionSimulatorP5(
    private val trustedPins: Set<String>
) {
    fun inspect(receivedPin: String): String {
        return if (receivedPin in trustedPins) {
            "Connessione sicura: pin riconosciuto"
        } else {
            "Possibile MITM: pin non riconosciuto"
        }
    }
}

// Caso d'uso di base: controlliamo una connessione valida e una sospetta.
fun demoL21P5MITMScenario(): List<String> {
    val detector = MITMDetectionSimulatorP5(
        trustedPins = setOf("pin-main", "pin-backup")
    )

    return listOf(
        detector.inspect("pin-main"),
        detector.inspect("unknown-pin")
    )
}