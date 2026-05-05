// L22 - P4: JWT token decoding.
// The example extracts payload information in a simple way.

import java.util.Base64

data class JwtPayloadP4(
    val subject: String,
    val role: String,
    val exp: Long
)

data class JwtDecodeResultP4(
    val payload: JwtPayloadP4?,
    val error: String?
)

class JwtDecoderSimulatorP4 {
    fun decode(token: String): JwtDecodeResultP4 {
        val parts = token.split(".")
        if (parts.size != 3) {
            return JwtDecodeResultP4(payload = null, error = "Invalid JWT: 3 parts are required")
        }

        val payloadPart = parts[1]
        val json = try {
            String(Base64.getUrlDecoder().decode(payloadPart))
        } catch (_: IllegalArgumentException) {
            return JwtDecodeResultP4(payload = null, error = "Invalid JWT: corrupted Base64URL payload")
        }

        val subject = Regex("\"sub\"\\s*:\\s*\"([^\"]+)\"").find(json)?.groupValues?.get(1) ?: ""
        val role = Regex("\"role\"\\s*:\\s*\"([^\"]+)\"").find(json)?.groupValues?.get(1) ?: ""
        val exp = Regex("\\\"exp\\\"\\s*:\\s*([0-9]+)").find(json)?.groupValues?.get(1)?.toLongOrNull()

        if (subject.isBlank() || role.isBlank() || exp == null) {
            return JwtDecodeResultP4(payload = null, error = "Invalid JWT: missing required claims")
        }

        return JwtDecodeResultP4(
            payload = JwtPayloadP4(subject = subject, role = role, exp = exp),
            error = null
        )
    }

    fun isExpired(payload: JwtPayloadP4, nowEpochSeconds: Long): Boolean {
        return payload.exp <= nowEpochSeconds
    }
}

// Basic use case: decodifichiamo a JWT with payload minimalist.
fun demoL22P4JWTDecode(): List<String> {
    val decoder = JwtDecoderSimulatorP4()
    val payloadJson = "{" + "\"sub\":\"student-1\",\"role\":\"admin\",\"exp\":2000}"
    val payloadBase64 = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.toByteArray())
    val validToken = "header.$payloadBase64.signature"

    val okResult = decoder.decode(validToken)
    val expiredInfo = okResult.payload?.let { decoder.isExpired(it, nowEpochSeconds = 2500) }

    val invalidTokenResult = decoder.decode("invalid-token")

    return listOf(
        "Decode valido: ${okResult.error == null}",
        "Subject: ${okResult.payload?.subject}",
        "Role: ${okResult.payload?.role}",
        "Scaduto: $expiredInfo",
        "Errore token malformato: ${invalidTokenResult.error}"
    )
}

fun main() {
    println("=== JWT Decode ===")
    demoL22P4JWTDecode().forEach { println(it) }
}
