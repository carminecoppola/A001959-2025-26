// L21 - P3: traffico in chiaro.
// L'obiettivo e rilevare e bloccare gli endpoint non sicuri.

class CleartextTrafficPolicyP3 {
    fun isAllowed(url: String): Boolean {
        return url.startsWith("https://")
    }

    fun describe(url: String): String {
        return if (isAllowed(url)) {
            "Traffico sicuro consentito per $url"
        } else {
            "Traffico in chiaro bloccato per $url"
        }
    }
}

// Caso d'uso di base: controlliamo un URL sicuro e uno insicuro.
fun demoL21P3CleartextTraffic(): List<String> {
    val policy = CleartextTrafficPolicyP3()
    return listOf(
        policy.describe("https://secure.example.com"),
        policy.describe("http://insecure.example.com")
    )
}

fun main() {
    println("=== Cleartext Traffic ===")
    val results = demoL21P3CleartextTraffic()
    results.forEach { println(it) }
}