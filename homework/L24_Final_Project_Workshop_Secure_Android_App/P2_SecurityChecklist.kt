// L24 - P2: checklist di sicurezza.
// Questa lista aiuta a verificare i punti principali prima del rilascio.

class SecurityChecklistSimulatorP2 {
    private val checklist = listOf(
        "HTTPS attivo",
        "Token salvati in modo sicuro",
        "Permessi minimi",
        "Log sensibili rimossi",
        "Backup verificato"
    )

    fun items(): List<String> = checklist

    fun completedItems(completed: Set<String>): List<String> {
        return checklist.filter { item -> item in completed }
    }
}

// Caso d'uso di base: controlliamo alcuni elementi gia completati.
fun demoL24P2SecurityChecklist(): List<String> {
    val simulator = SecurityChecklistSimulatorP2()
    return simulator.completedItems(setOf("HTTPS attivo", "Permessi minimi", "Backup verificato"))
}