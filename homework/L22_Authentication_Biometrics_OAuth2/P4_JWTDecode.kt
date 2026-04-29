// L22 - P4: decodifica di un token JWT.
// L'esempio estrae le informazioni dal payload in modo semplice.

import java.util.Base64

data class JwtPayloadP4(
    val subject: String,
    val role: String
)

class JwtDecoderSimulatorP4 {
    fun decode(token: String): JwtPayloadP4 {
        val parts = token.split(".")
        val payloadPart = parts.getOrNull(1).orEmpty()
        val json = String(Base64.getUrlDecoder().decode(payloadPart))

        val subject = Regex("\"sub\"\s*:\s*\"([^\"]+)\"").find(json)?.groupValues?.get(1) ?: ""
        val role = Regex("\"role\"\s*:\s*\"([^\"]+)\"").find(json)?.groupValues?.get(1) ?: ""

        return JwtPayloadP4(subject = subject, role = role)
    }
}

// Caso d'uso di base: decodifichiamo un JWT con payload minimalista.
fun demoL22P4JWTDecode(): JwtPayloadP4 {
    val payloadJson = "{" + "\"sub\":\"student-1\",\"role\":\"admin\"}"
    val payloadBase64 = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.toByteArray())
    val token = "header.$payloadBase64.signature"
    return JwtDecoderSimulatorP4().decode(token)
}