// L22 - P3: flusso OAuth2.
// Questo esempio mostra le fasi principali: autorizzazione, scambio del codice e token.

data class OAuth2StateP3(
    val authorizationCode: String?,
    val accessToken: String?,
    val refreshToken: String?
)

data class OAuth2TokenPairP3(
    val accessToken: String,
    val refreshToken: String
)

class OAuth2AuthorizationServerP3 {
    private val validClientId = "mobile-client"
    private val validClientSecret = "secret-xyz"
    private val issuedCodes = mutableSetOf<String>()

    fun requestAuthorization(clientId: String, userAccepted: Boolean): String? {
        if (!userAccepted || clientId != validClientId) {
            return null
        }
        val code = "auth-code-xyz"
        issuedCodes.add(code)
        return code
    }

    fun exchangeCodeForToken(code: String?, clientSecret: String): OAuth2TokenPairP3? {
        if (code.isNullOrBlank() || clientSecret != validClientSecret || code !in issuedCodes) {
            return null
        }
        return OAuth2TokenPairP3(
            accessToken = "access-token-from-$code",
            refreshToken = "refresh-token-from-$code"
        )
    }
}

class OAuth2ClientSimulatorP3(
    private val clientId: String,
    private val clientSecret: String,
    private val server: OAuth2AuthorizationServerP3
) {
    fun runFlow(userAccepted: Boolean): OAuth2StateP3 {
        val code = server.requestAuthorization(clientId = clientId, userAccepted = userAccepted)
        val tokenPair = server.exchangeCodeForToken(code = code, clientSecret = clientSecret)

        return OAuth2StateP3(
            authorizationCode = code,
            accessToken = tokenPair?.accessToken,
            refreshToken = tokenPair?.refreshToken
        )
    }
}

// Caso d'uso di base: eseguiamo il flusso con conferma utente e senza conferma.
fun demoL22P3OAuth2Flow(): List<OAuth2StateP3> {
    val server = OAuth2AuthorizationServerP3()
    val client = OAuth2ClientSimulatorP3(
        clientId = "mobile-client",
        clientSecret = "secret-xyz",
        server = server
    )

    return listOf(
        client.runFlow(userAccepted = true),
        client.runFlow(userAccepted = false)
    )
}

fun main() {
    println("=== OAuth2 Flow ===")
    val results = demoL22P3OAuth2Flow()
    results.forEach { println(it) }
}