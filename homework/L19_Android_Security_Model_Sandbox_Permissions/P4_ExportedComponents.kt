// L19 - P4: componenti esportati.
// Un componente esportato puo essere richiamato da altre app, quindi va limitato con attenzione.

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
            "${component.name} e accessibile da altre app"
        } else {
            "${component.name} e protetto o richiede una permission"
        }
    }
}

// Caso d'uso di base: confrontiamo un componente aperto e uno protetto.
fun demoL19P4ExportedComponents(): List<String> {
    val policy = ExportedComponentPolicyP4()
    return listOf(
        policy.explain(ExportedComponentP4("MainActivity", exported = false, requiresPermission = false)),
        policy.explain(ExportedComponentP4("ShareReceiver", exported = true, requiresPermission = true))
    )
}