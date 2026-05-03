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

// Fallback PIN-based authentication (simulato)
class FallbackPINAuthenticatorP1 {
    private val correctPIN = "1234"

    fun authenticateWithPIN(userInput: String): Boolean {
        return userInput == correctPIN
    }

    fun describe(success: Boolean): String {
        return if (success) "✓ PIN verificato" else "✗ PIN errato"
    }
}

// Caso d'uso di base: verifichiamo la disponibilita e simuliamo l'autenticazione.
fun demoL22P1BiometricPromptSetup(): List<String> {
    val prompt = BiometricPromptSimulatorP1()
    prompt.canAuthenticate(sensorAvailable = true)
    
    // Tentativo 1: Biometrico fallisce → Fallback PIN
    val biometricFails = prompt.authenticate(userConfirmed = false)
    val fallbackAuth = FallbackPINAuthenticatorP1()
    val pinResult = fallbackAuth.authenticateWithPIN("1234")
    
    // Tentativo 2: Biometrico succede
    val biometricSucceeds = prompt.authenticate(userConfirmed = true)
    
    return listOf(
        "Biometrico fallisce: ${biometricFails}",
        "Fallback: ${fallbackAuth.describe(pinResult)}",
        "Biometrico succede: ${biometricSucceeds}"
    )
}

fun main() {
    println("=== Biometric Prompt Setup ===")
    val results = demoL22P1BiometricPromptSetup()
    results.forEach { println(it) }
}