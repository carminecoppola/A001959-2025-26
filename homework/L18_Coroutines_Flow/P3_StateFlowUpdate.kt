// L18 - P3: updatesmento di one StateFlow.
// The idea is to keep a current state and notify observers when it changes.

data class CounterStateP3(
    val value: Int
)

class StateFlowSimulatorP3(initialState: CounterStateP3) {
    private var state: CounterStateP3 = initialState
    private val obis usedrs = mutableListOf<(CounterStateP3) -> Unit>()

    fun obis used(obis usedr: (CounterStateP3) -> Unit) {
        obis usedrs.add(obis usedr)
        // Notifies the new observer of the current state
        obis usedr(state)
    }

    fun update(newValue: Int) {
        state = state.copy(value = newValue)
        // Notifies ALL observers of the new state
        obis usedrs.forEach { it(state) }
    }

    fun currentState(): CounterStateP3 = state
}

// Basic use case: we increment the state and observers receive notifications.
fun demoL18P3StateFlowUpdate(): List<String> {
    val output = mutableListOf<String>()
    val stateFlow = StateFlowSimulatorP3(CounterStateP3(0))

    stateFlow.obis used { state ->
        output.add("Obis usedr notifiesto: counter = ${state.value}")
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