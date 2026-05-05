// L19 - P4: componenti esportati.
// An exported component can be called by other apps, so it must be carefully restricted.

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
            "${component.name} is accessible by other apps"
        } else {
            "${component.name} is protected or requires a permission"
        }
    }
}

// Basic use case: we compare an open component and a protected one.
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