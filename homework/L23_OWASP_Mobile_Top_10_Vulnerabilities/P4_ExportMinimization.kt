// L23 - P4: minimizzazione dell'esportazione.
// The goal is to expose only strictly necessary components.

data class AppComponentP4(
    val name: String,
    val exported: Boolean,
    val requiresExplicitExport: Boolean = false
)

class ExportMinimizationSimulatorP4 {
    fun minimize(components: List<AppComponentP4>): List<AppComponentP4> {
        return components.map { component ->
            if (component.name.startsWith("Internal") || component.requiresExplicitExport) {
                component.copy(exported = false)
            } else {
                component
            }
        }
    }
}

// Basic use case: riduciamo l'esportazione dei componenti interni.
fun demoL23P4ExportMinimization(): List<AppComponentP4> {
    val components = listOf(
        AppComponentP4("InternalSettingsActivity", exported = true),
        AppComponentP4("ShareActivity", exported = true),
        AppComponentP4("PublicHelpActivity", exported = true, requiresExplicitExport = true)
    )

    return ExportMinimizationSimulatorP4().minimize(components)
}

fun main() {
    println("=== Export Minimization ===")
    val results = demoL23P4ExportMinimization()
    results.forEach { println("${it.name}: exported=${it.exported}") }
}