// L18 - P2: raccolta dei dati da un Flow.
// Qui simuliamo un flusso che produce valori e un collector che aggiorna la UI.

class SimpleFlowP2<T>(private val values: List<T>) {
    fun collect(collector: (T) -> Unit) {
        values.forEach { value -> collector(value) }
    }
}

// Caso d'uso di base: raccogliamo il flusso e trasformiamo i valori per la UI.
fun demoL18P2FlowCollection(): List<String> {
    val flow = SimpleFlowP2(listOf("uno", "due", "tre"))
    val uiUpdates = mutableListOf<String>()

    flow.collect { value ->
        uiUpdates.add("UI aggiornata con: $value")
    }

    return uiUpdates
}