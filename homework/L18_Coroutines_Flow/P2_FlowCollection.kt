// L18 - P2: collection dei data da a Flow.
// Here we simulate a flow that produces values and a collector that updates the UI.

class SimpleFlowP2<T>(private val values: List<T>) {
    fun collect(collector: (T) -> Unit) {
        values.forEach { value -> collector(value) }
    }
}

// Basic use case: we collect the flow and transform values for the UI.
fun demoL18P2FlowCollection(): List<String> {
    val flow = SimpleFlowP2(listOf("one", "two", "three"))
    val uiUpdates = mutableListOf<String>()

    flow.collect { value ->
        uiUpdates.add("UI updated with: $value")
    }

    return uiUpdates
}

fun main() {
    println("=== Flow Collection ===")
    val updates = demoL18P2FlowCollection()
    updates.forEach { println(it) }
}