// Esercizio P5: click listener per gli elementi della lista.
// L'idea e associare un'azione a ogni elemento quando viene premuto.

data class ClickableItemP5(
    val id: Int,
    val title: String
)

class ClickListenerPatternAdapterP5(
    private val items: List<ClickableItemP5>,
    private val onItemClicked: (ClickableItemP5) -> String
) {
    // Qui impostiamo un click listener per ogni elemento della lista, che esegue
    // un'azione quando l'elemento viene cliccato.
    fun click(position: Int): String {
        val item = items.getOrNull(position)
            ?: return "Posizione non valida"

        return onItemClicked(item)
    }

    fun renderItems(): List<String> {
        return items.map { item -> "Elemento: ${item.title}" }
    }
}

// Caso d'uso di base: simuliamo il clic su un elemento della lista.
fun demoP5ClickListenerPattern(): List<String> {
    val items = listOf(
        ClickableItemP5(1, "Apri dettaglio"),
        ClickableItemP5(2, "Condividi elemento"),
        ClickableItemP5(3, "Elimina elemento")
    )

    val adapter = ClickListenerPatternAdapterP5(items) { item ->
        "Hai cliccato su: ${item.title}"
    }

    return listOf(
        adapter.click(0),
        adapter.click(2),
        adapter.click(10)
    )
}