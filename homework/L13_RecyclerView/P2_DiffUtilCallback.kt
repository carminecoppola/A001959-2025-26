// Exercise P2: simple implementation of a DiffUtil.Callback.
// DiffUtil compares two lists and updates only changed items,
// evitando di ridisegnare tutta the RecyclerView.

data class LessonItemP2(
    val id: Int,
    val title: String,
    val description: String
)

class SimpleDiffUtilCallbackP2(
    private val oldList: List<LessonItemP2>,
    private val newList: List<LessonItemP2>
) {
    fun getOldListSize(): Int = oldList.size

    fun getNewListSize(): Int = newList.size

    // Here we compare item identity to understand whether we are referring
    // of the same record even if the content changed.
    fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // Here we compare full content to verify whether data is actually identical.
    fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    // Educational helper to inspect differences between two lists.
    fun calculateChanges(): List<String> {
        val changes = mutableListOf<String>()
        val maxSize = maxOf(oldList.size, newList.size)

        for (index in 0 until maxSize) {
            val oldItem = oldList.getOrNull(index)
            val newItem = newList.getOrNull(index)

            when {
                oldItem == null && newItem != null -> {
                    changes.add("Inserito: ${newItem.title}")
                }
                oldItem != null && newItem == null -> {
                    changes.add("Rimosso: ${oldItem.title}")
                }
                oldItem != null && newItem != null -> {
                    if (!areItemsTheSame(index, index)) {
                        changes.add("Different item at position $index")
                    } else if (!areContentsTheSame(index, index)) {
                        changes.add("Aggiornato: ${oldItem.title}")
                    }
                }
            }
        }

        return changes
    }
}

// Basic use case: we compare an old list with a new one.
fun demoP2DiffUtilCallback(): List<String> {
    val oldList = listOf(
        LessonItemP2(1, "Apple", "Frutto rosso"),
        LessonItemP2(2, "Banana", "Frutto giallo")
    )

    val newList = listOf(
        LessonItemP2(1, "Apple", "Red and sweet fruit"),
        LessonItemP2(3, "Cherry", "Frutto piccolo")
    )

    val callback = SimpleDiffUtilCallbackP2(oldList, newList)
    return callback.calculateChanges()
}

fun main() {
    val results = demoP2DiffUtilCallback()
    results.forEach { println(it) }
}