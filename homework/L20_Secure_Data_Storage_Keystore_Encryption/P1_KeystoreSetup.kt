// L20 - P1: setup del Keystore.
// Questo esempio mostra come si puo rappresentare la gestione sicura di una chiave.

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

// Caso d'uso di base: creiamo una chiave e ne controlliamo la presenza.
fun demoL20P1KeystoreSetup(): List<String> {
    val keystore = KeystoreSimulatorP1()
    val key = keystore.generateKey("app_master_key")
    return listOf(
        "Chiave creata: ${key.alias}",
        "Presente nel keystore: ${keystore.hasKey(key.alias)}"
    )
}

fun main() {
    println("=== Keystore Setup ===")
    val results = demoL20P1KeystoreSetup()
    results.forEach { println(it) }
}