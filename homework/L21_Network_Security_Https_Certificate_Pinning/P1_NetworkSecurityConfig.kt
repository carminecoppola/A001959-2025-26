// L21 - P1: network security config.
// Questo esempio mostra l'idea di forzare HTTPS e bloccare configurazioni insicure.

data class NetworkSecurityPolicyP1(
    val enforceHttps: Boolean,
    val allowCleartext: Boolean
)

class NetworkSecurityConfigSimulatorP1 {
    fun validate(policy: NetworkSecurityPolicyP1): String {
        return if (policy.enforceHttps && !policy.allowCleartext) {
            "Configurazione sicura: solo HTTPS consentito"
        } else {
            "Configurazione debole: il traffico in chiaro non e bloccato"
        }
    }
}

// Caso d'uso di base: confrontiamo una policy sicura con una insicura.
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