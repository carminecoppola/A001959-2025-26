// Esercizio P5: Flow che arriva dal database Room.
// Qui simuliamo un flusso di dati che si aggiorna nel tempo.

class SimpleRoomFlowP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val collectors = mutableListOf<(T) -> Unit>()

    fun collect(collector: (T) -> Unit) {
        collectors.add(collector)
        collector(value)
    }

    fun update(newValue: T) {
        value = newValue
        collectors.forEach { collector -> collector(value) }
    }
}

// Caso d'uso di base: osserviamo gli aggiornamenti del flusso dati.
fun demoP5FlowFromRoom(): List<String> {
    val flow = SimpleRoomFlowP5(0)
    val output = mutableListOf<String>()

    flow.collect { value ->
        output.add("Valore letto dal flow: $value")
    }

    flow.update(1)
    flow.update(2)

    return output
}