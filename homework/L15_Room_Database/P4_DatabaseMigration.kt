// Esercizio P4: migrazione del database.
// La migrazione spiega come passare da una versione del database a un'altra.

data class SchemaVersionP4(
    val version: Int,
    val columns: List<String>
)

class DatabaseMigrationPlanP4 {
    fun migrate(fromVersion: SchemaVersionP4, toVersion: SchemaVersionP4): String {
        return if (fromVersion.version < toVersion.version) {
            "Migrazione da v${fromVersion.version} a v${toVersion.version}: aggiungere colonne ${toVersion.columns}"
        } else {
            "Nessuna migrazione necessaria"
        }
    }
}

// Caso d'uso di base: descriviamo una migrazione tra due versioni.
fun demoP4DatabaseMigration(): String {
    val current = SchemaVersionP4(1, listOf("id", "name"))
    val target = SchemaVersionP4(2, listOf("id", "name", "email"))
    return DatabaseMigrationPlanP4().migrate(current, target)
}