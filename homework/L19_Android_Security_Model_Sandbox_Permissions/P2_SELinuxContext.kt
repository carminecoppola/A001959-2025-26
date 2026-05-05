// L19 - P2: context SELinux.
// In Android, SELinux context is not directly managed by the app, but we can
// model the idea of security rules that limit access to resources.

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
            "Access withsentito a $resource nel dominio ${context.domain}"
        } else {
            "Accesso negato a $resource nel dominio ${context.domain}"
        }
    }
}

// Basic use case: we check access to two different resources.
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

fun main() {
    println("=== SELinux Context ===")
    val results = demoL19P2SELinuxContext()
    results.forEach { println(it) }
}