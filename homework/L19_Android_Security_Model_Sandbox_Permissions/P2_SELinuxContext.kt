// L19 - P2: contesto SELinux.
// In Android il contesto SELinux non viene gestito direttamente dall'app, ma possiamo
// modellare l'idea di regole di sicurezza che limitano l'accesso alle risorse.

data class SecurityContextP2(
    val domain: String,
    val allowedResources: Set<String>
)

class SELinuxContextSimulatorP2(
    private val context: SecurityContextP2
) {
    fun canAccess(resource: String): Boolean {
        return resource in context.allowedResources
    }

    fun describeAccess(resource: String): String {
        return if (canAccess(resource)) {
            "Accesso consentito a $resource nel dominio ${context.domain}"
        } else {
            "Accesso negato a $resource nel dominio ${context.domain}"
        }
    }
}

// Caso d'uso di base: controlliamo l'accesso a due risorse diverse.
fun demoL19P2SELinuxContext(): List<String> {
    val context = SecurityContextP2(
        domain = "untrusted_app",
        allowedResources = setOf("cache", "files")
    )

    val simulator = SELinuxContextSimulatorP2(context)
    return listOf(
        simulator.describeAccess("cache"),
        simulator.describeAccess("system_settings")
    )
}