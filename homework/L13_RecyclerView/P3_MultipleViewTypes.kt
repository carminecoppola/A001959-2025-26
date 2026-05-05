// Exercise P3: handling multiple view types in the same list.
// In a real RecyclerView this is used when items have different layouts.

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

    // Here we check the item type and return a different layout
    // for each type.
    fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is FeedItemP3.TextItem -> VIEW_TYPE_TEXT
            is FeedItemP3.ImageItem -> VIEW_TYPE_IMAGE
            is FeedItemP3.DividerItem -> VIEW_TYPE_DIVIDER
        }
    }

    // This function simulates creating a different layout based on type.
    fun onCreateViewHolder(viewType: Int): String {
        return when (viewType) {
            VIEW_TYPE_TEXT -> "Creata ViewHolder testuale"
            VIEW_TYPE_IMAGE -> "Created image ViewHolder"
            VIEW_TYPE_DIVIDER -> "Creata ViewHolder divisore"
            else -> "Tipo sconosciuto"
        }
    }

    // This function simulates binding between data and view.
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

// Basic use case: costruiamo a feed with items different.
fun demoP3MultipleViewTypes(): List<String> {
    val feedItems = listOf(
        FeedItemP3.TextItem(1, "Welcome to the feed"),
        FeedItemP3.ImageItem(2, "https://example.com/image.png", "An image example"),
        FeedItemP3.DividerItem(3),
        FeedItemP3.TextItem(4, "Altro messaggio di text")
    )

    val adapter = MultipleViewTypesAdapterP3(feedItems)
    return adapter.renderFeed()
}

fun main() {
    val results = demoP3MultipleViewTypes()
    results.forEach { println(it) }
}