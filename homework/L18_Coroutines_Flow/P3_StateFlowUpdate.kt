// L18 - P3: aggiornamento di uno StateFlow.
// L'idea e mantenere uno stato corrente e notificare gli observer quando cambia.

data class CounterStateP3(
    val value: Int
)

class StateFlowSimulatorP3(initialState: CounterStateP3) {
    private var state: CounterStateP3 = initialState
    private val observers = mutableListOf<(CounterStateP3) -> Unit>()

    fun observe(observer: (CounterStateP3) -> Unit) {
        observers.add(observer)
        // Notifica il nuovo observer dello stato corrente
        observer(state)
    }

    fun update(newValue: Int) {
        state = state.copy(value = newValue)
        // Notifica TUTTI gli observer del nuovo stato
        observers.forEach { it(state) }
    }

    fun currentState(): CounterStateP3 = state
}

// Caso d'uso di base: incrementiamo lo stato e gli observer ricevono notifiche.
fun demoL18P3StateFlowUpdate(): List<String> {
    val output = mutableListOf<String>()
    val stateFlow = StateFlowSimulatorP3(CounterStateP3(0))

    stateFlow.observe { state ->
        output.add("Observer notificato: counter = ${state.value}")
    }

    stateFlow.update(1)
    stateFlow.update(2)

    return output
}

fun main() {
    println("=== StateFlow Update ===")
    val results = demoL18P3StateFlowUpdate()
    results.forEach { println(it) }
}