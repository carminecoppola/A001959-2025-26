// L18 - P5: operatore combine().
// Qui mostriamo l'idea di combinare due flussi per produrre un unico risultato.
// Quando UNA fonte cambia, il risultato combinato si aggiorna.

class SimpleCombineFlowP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val collectors = mutableListOf<(T) -> Unit>()

    fun collect(collector: (T) -> Unit) {
        collectors.add(collector)
        collector(value)
    }

    fun emit(newValue: T) {
        value = newValue
        collectors.forEach { it(newValue) }
    }

    fun getValue(): T = value
}

class CombineOperatorSimulatorP5 {
    // Combinazione reattiva: quando uno dei flussi cambia, aggiorniamo il risultato
    fun combineFlows(
        left: SimpleCombineFlowP5<String>,
        right: SimpleCombineFlowP5<String>,
        onCombined: (String) -> Unit
    ) {
        // Osserviamo il primo flusso
        left.collect { leftValue ->
            val combined = "$leftValue | ${right.getValue()}"
            onCombined(combined)
        }

        // Osserviamo il secondo flusso
        right.collect { rightValue ->
            val combined = "${left.getValue()} | $rightValue"
            onCombined(combined)
        }
    }
}

// Caso d'uso di base: combiniamo due flussi reattivamente.
fun demoL18P5CombineOperator(): List<String> {
    val output = mutableListOf<String>()

    val leftFlow = SimpleCombineFlowP5("A")
    val rightFlow = SimpleCombineFlowP5("1")

    val combiner = CombineOperatorSimulatorP5()
    combiner.combineFlows(leftFlow, rightFlow) { combined ->
        output.add(combined)
    }

    // Quando cambia uno dei flussi, il risultato si aggiorna automaticamente
    output.add("--- Dopo aggiornamento leftFlow ---")
    leftFlow.emit("B")

    output.add("--- Dopo aggiornamento rightFlow ---")
    rightFlow.emit("2")

    return output
}

fun main() {
    println("=== Combine Operator ===")
    val results = demoL18P5CombineOperator()
    results.forEach { println(it) }
}