// L20 - P1: setup of the Keystore.
// This example shows how secure key management can be represented.

data class KeyAliasP1(
    val alias: String,
    val createdAt: String
)

class KeystoreSimulatorP1 {
    private val keys = mutableMapOf<String, String>()

    fun generateKey(alias: String): KeyAliasP1 {
        keys[alias] = "secret-key-material"
        return KeyAliasP1(alias = alias, createdAt = "2026-04-29")
    }

    fun hasKey(alias: String): Boolean {
        return alias in keys
    }
}

// Basic use case: we create a key and check its presence.
fun demoL20P1KeystoreSetup(): List<String> {
    val keystore = KeystoreSimulatorP1()
    val key = keystore.generateKey("app_master_key")
    return listOf(
        "Chiave creata: ${key.alias}",
        "Present in keystore: ${keystore.hasKey(key.alias)}"
    )
}

fun main() {
    println("=== Keystore Setup ===")
    val results = demoL20P1KeystoreSetup()
    results.forEach { println(it) }
}