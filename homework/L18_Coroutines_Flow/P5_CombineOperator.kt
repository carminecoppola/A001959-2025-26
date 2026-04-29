// L18 - P5: operatore combine().
// Qui mostriamo l'idea di combinare due flussi per produrre un unico risultato.

class CombineOperatorSimulatorP5 {
    // Uniamo due liste elemento per elemento, come se fossero due flussi sincronizzati.
    fun combine(first: List<String>, second: List<String>): List<String> {
        val result = mutableListOf<String>()
        val maxSize = maxOf(first.size, second.size)

        for (index in 0 until maxSize) {
            val left = first.getOrNull(index) ?: ""
            val right = second.getOrNull(index) ?: ""
            result.add("$left | $right")
        }

        return result
    }
}

// Caso d'uso di base: combiniamo due sequenze in una sola.
fun demoL18P5CombineOperator(): List<String> {
    val leftFlow = listOf("A", "B", "C")
    val rightFlow = listOf("1", "2", "3")
    return CombineOperatorSimulatorP5().combine(leftFlow, rightFlow)
}