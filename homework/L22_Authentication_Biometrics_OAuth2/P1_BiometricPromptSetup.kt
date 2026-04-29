// L22 - P1: setup di BiometricPrompt.
// Questo esempio mostra il flusso concettuale: inizializzare, verificare e autenticare.

enum class BiometricStatusP1 {
    AVAILABLE,
    UNAVAILABLE,
    AUTHENTICATED,
    FAILED
}

class BiometricPromptSimulatorP1 {
    private var lastStatus: BiometricStatusP1 = BiometricStatusP1.UNAVAILABLE

    fun canAuthenticate(sensorAvailable: Boolean): Boolean {
        lastStatus = if (sensorAvailable) BiometricStatusP1.AVAILABLE else BiometricStatusP1.UNAVAILABLE
        return sensorAvailable
    }

    fun authenticate(userConfirmed: Boolean): BiometricStatusP1 {
        lastStatus = if (userConfirmed) BiometricStatusP1.AUTHENTICATED else BiometricStatusP1.FAILED
        return lastStatus
    }

    fun currentStatus(): BiometricStatusP1 = lastStatus
}

// Caso d'uso di base: verifichiamo la disponibilita e simuliamo l'autenticazione.
fun demoL22P1BiometricPromptSetup(): List<BiometricStatusP1> {
    val prompt = BiometricPromptSimulatorP1()
    prompt.canAuthenticate(sensorAvailable = true)
    val firstAttempt = prompt.authenticate(userConfirmed = true)
    val secondAttempt = prompt.authenticate(userConfirmed = false)
    return listOf(prompt.currentStatus(), firstAttempt, secondAttempt)
}