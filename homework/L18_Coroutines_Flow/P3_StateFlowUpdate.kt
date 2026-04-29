// L18 - P3: aggiornamento di uno StateFlow.
// L'idea e mantenere uno stato corrente e aggiornarlo quando serve.

data class CounterStateP3(
    val value: Int
)

class StateFlowSimulatorP3(initialState: CounterStateP3) {
    private var state: CounterStateP3 = initialState

    fun update(newValue: Int) {
        state = state.copy(value = newValue)
    }

    fun currentState(): CounterStateP3 = state
}

// Caso d'uso di base: incrementiamo lo stato corrente.
fun demoL18P3StateFlowUpdate(): CounterStateP3 {
    val stateFlow = StateFlowSimulatorP3(CounterStateP3(0))
    stateFlow.update(1)
    stateFlow.update(2)
    return stateFlow.currentState()
}