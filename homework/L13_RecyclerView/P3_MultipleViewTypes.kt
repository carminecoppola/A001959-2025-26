// Esercizio P3: gestione di piu tipi di vista nello stesso elenco.
// In una RecyclerView reale questo serve quando gli elementi hanno layout diversi.

sealed class FeedItemP3 {
    data class TextItem(val id: Int, val message: String) : FeedItemP3()
    data class ImageItem(val id: Int, val imageUrl: String, val caption: String) : FeedItemP3()
    data class DividerItem(val id: Int) : FeedItemP3()
}

class MultipleViewTypesAdapterP3(
    private val items: List<FeedItemP3>
) {
    companion object {
        const val VIEW_TYPE_TEXT = 1
        const val VIEW_TYPE_IMAGE = 2
        const val VIEW_TYPE_DIVIDER = 3
    }

    // Qui stiamo verificando il tipo di elemento e restituendo un layout diverso
    // per ogni tipo.
    fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is FeedItemP3.TextItem -> VIEW_TYPE_TEXT
            is FeedItemP3.ImageItem -> VIEW_TYPE_IMAGE
            is FeedItemP3.DividerItem -> VIEW_TYPE_DIVIDER
        }
    }

    // Questa funzione simula la creazione di un layout diverso in base al tipo.
    fun onCreateViewHolder(viewType: Int): String {
        return when (viewType) {
            VIEW_TYPE_TEXT -> "Creata ViewHolder testuale"
            VIEW_TYPE_IMAGE -> "Creata ViewHolder immagine"
            VIEW_TYPE_DIVIDER -> "Creata ViewHolder divisore"
            else -> "Tipo sconosciuto"
        }
    }

    // Questa funzione simula il collegamento tra dati e vista.
    fun onBindViewHolder(position: Int): String {
        return when (val item = items[position]) {
            is FeedItemP3.TextItem -> "Testo #${item.id}: ${item.message}"
            is FeedItemP3.ImageItem -> "Immagine #${item.id}: ${item.imageUrl} - ${item.caption}"
            is FeedItemP3.DividerItem -> "Divisore #${item.id}"
        }
    }

    fun renderFeed(): List<String> {
        val output = mutableListOf<String>()

        for (position in items.indices) {
            val viewType = getItemViewType(position)
            output.add(onCreateViewHolder(viewType))
            output.add(onBindViewHolder(position))
        }

        return output
    }
}

// Caso d'uso di base: costruiamo un feed con elementi diversi.
fun demoP3MultipleViewTypes(): List<String> {
    val feedItems = listOf(
        FeedItemP3.TextItem(1, "Benvenuto nel feed"),
        FeedItemP3.ImageItem(2, "https://example.com/image.png", "Un esempio di immagine"),
        FeedItemP3.DividerItem(3),
        FeedItemP3.TextItem(4, "Altro messaggio di testo")
    )

    val adapter = MultipleViewTypesAdapterP3(feedItems)
    return adapter.renderFeed()
}

fun main() {
    val results = demoP3MultipleViewTypes()
    results.forEach { println(it) }
}