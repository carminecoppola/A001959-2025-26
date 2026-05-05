// L18 - P5: operatore combine().
// Here we show the idea of combining two flows to produce one result.
// When ONE source changes, the combined result updates.

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
    // Reactive combination: when one flow changes, we update the result
    fun combineFlows(
        left: SimpleCombineFlowP5<String>,
        right: SimpleCombineFlowP5<String>,
        onCombined: (String) -> Unit
    ) {
        // Osserviamo the first flow
        left.collect { leftValue ->
            val combined = "$leftValue | ${right.getValue()}"
            onCombined(combined)
        }

        // Osserviamo the second flow
        right.collect { rightValue ->
            val combined = "${left.getValue()} | $rightValue"
            onCombined(combined)
        }
    }
}

// Basic use case: combiniamo two flows reattivamente.
fun demoL18P5CombineOperator(): List<String> {
    val output = mutableListOf<String>()

    val leftFlow = SimpleCombineFlowP5("A")
    val rightFlow = SimpleCombineFlowP5("1")

    val combiner = CombineOperatorSimulatorP5()
    combiner.combineFlows(leftFlow, rightFlow) { combined ->
        output.add(combined)
    }

    // When one flow changes, the result updates automatically
    output.add("--- Dopo updatesmento leftFlow ---")
    leftFlow.emit("B")

    output.add("--- Dopo updatesmento rightFlow ---")
    rightFlow.emit("2")

    return output
}

fun main() {
    println("=== Combine Operator ===")
    val results = demoL18P5CombineOperator()
    results.forEach { println(it) }
}