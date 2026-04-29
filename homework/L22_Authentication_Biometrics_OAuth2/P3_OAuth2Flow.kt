// L22 - P3: flusso OAuth2.
// Questo esempio mostra le fasi principali: autorizzazione, scambio del codice e token.

data class OAuth2StateP3(
    val authorizationCode: String?,
    val accessToken: String?
)

class OAuth2FlowSimulatorP3 {
    fun requestAuthorization(userAccepted: Boolean): String? {
        return if (userAccepted) "auth-code-xyz" else null
    }

    fun exchangeCodeForToken(code: String?): String? {
        return if (code.isNullOrBlank()) null else "access-token-from-$code"
    }

    fun runFlow(userAccepted: Boolean): OAuth2StateP3 {
        val code = requestAuthorization(userAccepted)
        val token = exchangeCodeForToken(code)
        return OAuth2StateP3(code, token)
    }
}

// Caso d'uso di base: eseguiamo il flusso con conferma utente e senza conferma.
fun demoL22P3OAuth2Flow(): List<OAuth2StateP3> {
    val simulator = OAuth2FlowSimulatorP3()
    return listOf(
        simulator.runFlow(userAccepted = true),
        simulator.runFlow(userAccepted = false)
    )
}