// L20 - P4: setup di SQLCipher.
// SQLCipher cifra il database: qui simuliamo una connessione protetta e una query.

data class SecureDatabaseConfigP4(
    val databaseName: String,
    val passwordSet: Boolean
)

class SQLCipherSetupSimulatorP4 {
    fun createConfig(databaseName: String, password: String): SecureDatabaseConfigP4 {
        return SecureDatabaseConfigP4(
            databaseName = databaseName,
            passwordSet = password.isNotEmpty()
        )
    }

    fun canOpenDatabase(config: SecureDatabaseConfigP4): Boolean {
        return config.passwordSet
    }

    fun openDatabase(databaseName: String, password: String?): String {
        return if (password != null && password.isNotEmpty()) {
            "✓ Database '$databaseName' aperto con successo (password corretta)"
        } else {
            "✗ Impossibile aprire '$databaseName': password mancante o errata"
        }
    }
}

// Caso d'uso di base: configuriamo un database cifrato e mostriamo il controllo di accesso.
fun demoL20P4SQLCipherSetup(): List<String> {
    val setup = SQLCipherSetupSimulatorP4()
    val output = mutableListOf<String>()

    output.add("=== SQLCipher Access Control ===")
    
    // Tentativo SENZA password
    output.add("\n--- Accesso senza password ---")
    output.add(setup.openDatabase("app.db", null))

    // Tentativo CON password errata
    output.add("\n--- Accesso con password errata ---")
    output.add(setup.openDatabase("app.db", "wrong-password"))

    // Accesso CON password corretta
    output.add("\n--- Accesso con password corretta ---")
    output.add(setup.openDatabase("app.db", "strong-password"))

    return output
}

fun main() {
    println("=== SQLCipher Setup ===")
    val results = demoL20P4SQLCipherSetup()
    results.forEach { println(it) }
}