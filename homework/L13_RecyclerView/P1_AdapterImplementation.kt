// Exercise P1: simple adapter implementation for RecyclerView.
// The goal is showsre the flow base: creare a ViewHolder, collegare i data
// and remaintuire the rappresentazione dell'item da showsre in list.

data class LessonItemP1(
    val id: Int,
    val title: String,
    val description: String
)

class SimpleRecyclerViewAdapterP1(
    private val items: List<LessonItemP1>
) {
    // This class simulates a RecyclerView ViewHolder.
    // In Android the ViewHolder contiene i riferimenti alle viste dell'item.
    class ViewHolder {
        private var currentItem: LessonItemP1? = null

        // Questa funzione collega i data al singolo item of the list.
        fun bind(item: LessonItemP1): String {
            currentItem = item
            return "[${item.id}] ${item.title} - ${item.description}"
        }

        // Return the content currently associated with the ViewHolder.
        fun getBoundItem(): LessonItemP1? = currentItem
    }

    // This function creates a ViewHolder object and links it to a specific layout
    // for each item in the list.
    fun onCreateViewHolder(): ViewHolder = ViewHolder()

    // This function gets data at the requested position and passes it to the ViewHolder.
    // This way the view shows the correct information.
    fun onBindViewHolder(holder: ViewHolder, position: Int): String {
        val item = items[position]
        return holder.bind(item)
    }

    fun getItemCount(): Int = items.size

    // Helper method to view the final result in a simple way.
    fun renderAllItems(): List<String> {
        val renderedItems = mutableListOf<String>()

        for (position in items.indices) {
            val holder = onCreateViewHolder()
            renderedItems.add(onBindViewHolder(holder, position))
        }

        return renderedItems
    }
}

// Basic use case: costruiamo a list and the facciamo renderizzare dall'adapter.
fun demoP1AdapterImplementation(): List<String> {
    val sampleItems = listOf(
        LessonItemP1(1, "First item", "This is the first data item in the list"),
        LessonItemP1(2, "Second item", "This is the second data item in the list")
    )

    val adapter = SimpleRecyclerViewAdapterP1(sampleItems)
    return adapter.renderAllItems()
}

fun main() {
    val results = demoP1AdapterImplementation()
    results.forEach { println(it) }
}