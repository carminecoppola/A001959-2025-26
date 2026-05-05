// L22 - P3: flow OAuth2.
// This example shows the main phases: authorization, code exchange, and token.

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
    private val is usedr: OAuth2AuthorizationServerP3
) {
    fun runFlow(userAccepted: Boolean): OAuth2StateP3 {
        val code = is usedr.requestAuthorization(clientId = clientId, userAccepted = userAccepted)
        val tokenPair = is usedr.exchangeCodeForToken(code = code, clientSecret = clientSecret)

        return OAuth2StateP3(
            authorizationCode = code,
            accessToken = tokenPair?.accessToken,
            refreshToken = tokenPair?.refreshToken
        )
    }
}

// Basic use case: we run the flow with and without user confirmation.
fun demoL22P3OAuth2Flow(): List<OAuth2StateP3> {
    val is usedr = OAuth2AuthorizationServerP3()
    val client = OAuth2ClientSimulatorP3(
        clientId = "mobile-client",
        clientSecret = "secret-xyz",
        is usedr = is usedr
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