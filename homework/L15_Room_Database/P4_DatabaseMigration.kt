// Exercise P4: database migration.
// Migration explains how to move from one database version to another while preserving data.

data class UserRecordP4(
    val id: Int,
    val name: String,
    val email: String? = null  // Aggiunto in v2
)

data class SchemaVersionP4(
    val version: Int,
    val columns: List<String>
)

class DatabaseMigrationPlanP4 {
    // Simula a migration da v1 (id, name) a v2 (id, name, email)
    fun migrateData(
        oldRecords: List<Map<String, String>>,
        fromVersion: Int,
        toVersion: Int
    ): List<Map<String, String?>> {
        return when {
            fromVersion == 1 && toVersion == 2 -> {
                // Aggiungiamo the campo 'email' with valore di default
                oldRecords.map { record ->
                    record.toMutableMap().apply {
                        put("email", "")  // Default vuoto for i record v1
                    }
                }
            }
            else -> oldRecords.map { it as Map<String, String?> }
        }
    }

    fun getMigrationDescription(fromVersion: SchemaVersionP4, toVersion: SchemaVersionP4): String {
        return if (fromVersion.version < toVersion.version) {
            val newColumns = toVersion.columns.subtract(fromVersion.columns.toSet())
            "Migrazione da v${fromVersion.version} a v${toVersion.version}: " +
            "aggiungere columns $newColumns with values di default"
        } else {
            "Nessuna migration necessaria"
        }
    }
}

// Basic use case: dewe write a migration and we show the trasformazione dei data.
fun demoP4DatabaseMigration(): List<String> {
    val output = mutableListOf<String>()

    val currentSchema = SchemaVersionP4(1, listOf("id", "name"))
    val targetSchema = SchemaVersionP4(2, listOf("id", "name", "email"))

    output.add(DatabaseMigrationPlanP4().getMigrationDescription(currentSchema, targetSchema))

    // Dati of the v1
    val oldData = listOf(
        mapOf("id" to "1", "name" to "Alice"),
        mapOf("id" to "2", "name" to "Bob")
    )

    output.add("\n--- Dati PRIMA of the migration (v1) ---")
    oldData.forEach { output.add(it.toString()) }

    // Eseguiamo the migration
    val migrator = DatabaseMigrationPlanP4()
    val newData = migrator.migrateData(oldData, 1, 2)

    output.add("\n--- Dati DOPO the migration (v2) ---")
    newData.forEach { output.add(it.toString()) }

    output.add("\n--- Risultato ---")
    output.add("v1 records are preserved, with 'email' added as an empty column")

    return output
}

fun main() {
    val results = demoP4DatabaseMigration()
    results.forEach { println(it) }
}