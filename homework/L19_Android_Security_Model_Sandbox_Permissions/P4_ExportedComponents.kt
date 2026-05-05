// L19 - P4: componenti esportati.
// Un componente esportato can essere richiamato da althree app, quindi must be limitato with attenzione.

data class ExportedComponentP4(
    val name: String,
    val exported: Boolean,
    val requiresPermission: Boolean
)

class ExportedComponentPolicyP4 {
    fun canBeCalledFromOutside(component: ExportedComponentP4): Boolean {
        return component.exported && !component.requiresPermission
    }

    fun explain(component: ExportedComponentP4): String {
        return if (canBeCalledFromOutside(component)) {
            "${component.name} and accessibile da althree app"
        } else {
            "${component.name} and protetto o richiede a permission"
        }
    }
}

// Basic use case: confrontiamo a componente aperto and one protetto.
fun demoL19P4ExportedComponents(): List<String> {
    val policy = ExportedComponentPolicyP4()
    return listOf(
        policy.explain(ExportedComponentP4("MainActivity", exported = false, requiresPermission = false)),
        policy.explain(ExportedComponentP4("ShareReceiver", exported = true, requiresPermission = true))
    )
}

fun main() {
    println("=== Exported Components ===")
    val results = demoL19P4ExportedComponents()
    results.forEach { println(it) }
}