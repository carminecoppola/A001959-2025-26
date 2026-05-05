// L21 - P3: cleartext traffic.
// The goal is to detect and block insecure endpoints.

class CleartextTrafficPolicyP3 {
    fun isAllowed(url: String): Boolean {
        return url.startsWith("https://")
    }

    fun describe(url: String): String {
        return if (isAllowed(url)) {
            "Secure traffic allowed for $url"
        } else {
            "Cleartext traffic blocked for $url"
        }
    }
}

// Basic use case: we check a secure URL and an insecure one.
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