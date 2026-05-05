// L21 - P1: network security config.
// This example shows l'idea di forzare HTTPS and block configurazioni insicure.

data class NetworkSecurityPolicyP1(
    val enforceHttps: Boolean,
    val allowCleartext: Boolean
)

class NetworkSecurityConfigSimulatorP1 {
    fun validate(policy: NetworkSecurityPolicyP1): String {
        return if (policy.enforceHttps && !policy.allowCleartext) {
            "Secure configuration: only HTTPS allowed"
        } else {
            "Weak configuration: cleartext traffic is not blocked"
        }
    }
}

// Basic use case: we compare a secure policy with an insecure one.
fun demoL21P1NetworkSecurityConfig(): List<String> {
    val simulator = NetworkSecurityConfigSimulatorP1()
    return listOf(
        simulator.validate(NetworkSecurityPolicyP1(enforceHttps = true, allowCleartext = false)),
        simulator.validate(NetworkSecurityPolicyP1(enforceHttps = false, allowCleartext = true))
    )
}

fun main() {
    println("=== Network Security Config ===")
    val results = demoL21P1NetworkSecurityConfig()
    results.forEach { println(it) }
}