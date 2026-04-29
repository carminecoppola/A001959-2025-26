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
}

// Caso d'uso di base: configuriamo un database cifrato.
fun demoL20P4SQLCipherSetup(): List<String> {
    val setup = SQLCipherSetupSimulatorP4()
    val config = setup.createConfig("app.db", "strong-password")
    return listOf(
        "Database: ${config.databaseName}",
        "Apertura consentita: ${setup.canOpenDatabase(config)}"
    )
}