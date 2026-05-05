// Exercise P5: click listener for list items.
// The idea is to attach an action to each item when pressed.

data class ClickableItemP5(
    val id: Int,
    val title: String
)

class ClickListenerPatternAdapterP5(
    private val items: List<ClickableItemP5>,
    private val onItemClicked: (ClickableItemP5) -> String
) {
    // Here we set a click listener for each list item, which executes
    // an action when the item is clicked.
    fun click(position: Int): String {
        val item = items.getOrNull(position)
            ?: return "Invalid position"

        return onItemClicked(item)
    }

    fun renderItems(): List<String> {
        return items.map { item -> "Elemento: ${item.title}" }
    }
}

// Basic use case: simuliamo the clic su a item of the list.
fun demoP5ClickListenerPattern(): List<String> {
    val items = listOf(
        ClickableItemP5(1, "Apri dettaglio"),
        ClickableItemP5(2, "Condividi item"),
        ClickableItemP5(3, "Elimina item")
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

fun main() {
    val results = demoP5ClickListenerPattern()
    results.forEach { println(it) }
}