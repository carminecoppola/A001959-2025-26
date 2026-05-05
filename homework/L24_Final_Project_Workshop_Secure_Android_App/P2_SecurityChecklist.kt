// L24 - P2: security checklist.
// This list helps verify the main points before release.

class SecurityChecklistSimulatorP2 {
    private val checklist = listOf(
        "HTTPS attivo",
        "Tokens stored securely",
        "Permessi minimi",
        "Log sensibili rimossi",
        "Backup verificato",
        "Biometria o PIN di fallback",
        "OAuth2 with refresh token",
        "JWT with expiration controlsta",
        "Componenti esportati minimizzati",
        "ProGuard/R8 attivo",
        "Nessun secret hardcoded"
    )

    fun items(): List<String> = checklist

    fun completedItems(completed: Set<String>): List<String> {
        return checklist.filter { item -> item in completed }
    }
}

// Basic use case: we check some already-completed items.
fun demoL24P2SecurityChecklist(): List<String> {
    val simulator = SecurityChecklistSimulatorP2()
    return simulator.completedItems(setOf("HTTPS attivo", "Permessi minimi", "Backup verificato", "Nessun secret hardcoded"))
}

fun main() {
    println("=== Security Checklist ===")
    val simulator = SecurityChecklistSimulatorP2()
    val completed = demoL24P2SecurityChecklist()
    println("Completati:")
    completed.forEach { println("- $it") }
    println("Totale checklist: ${simulator.items().size}")
}