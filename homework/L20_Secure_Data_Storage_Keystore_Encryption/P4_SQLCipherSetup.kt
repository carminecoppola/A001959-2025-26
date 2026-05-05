// L20 - P4: setup di SQLCipher.
// SQLCipher encrypts the database: here we simulate a protected connection and a query.

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
            "✓ Database '$databaseName' aperto with success (correct password)"
        } else {
            "✗ Impossibile aprire '$databaseName': password mancante o errata"
        }
    }
}

// Basic use case: we configure an encrypted database and show access control.
fun demoL20P4SQLCipherSetup(): List<String> {
    val setup = SQLCipherSetupSimulatorP4()
    val output = mutableListOf<String>()

    output.add("=== SQLCipher Access Control ===")
    
    // Tentativo SENZA password
    output.add("\n--- Access without password ---")
    output.add(setup.openDatabase("app.db", null))

    // Tentativo CON wrong password
    output.add("\n--- Access with wrong password ---")
    output.add(setup.openDatabase("app.db", "wrong-password"))

    // Accesso CON correct password
    output.add("\n--- Access with correct password ---")
    output.add(setup.openDatabase("app.db", "strong-password"))

    return output
}

fun main() {
    println("=== SQLCipher Setup ===")
    val results = demoL20P4SQLCipherSetup()
    results.forEach { println(it) }
}