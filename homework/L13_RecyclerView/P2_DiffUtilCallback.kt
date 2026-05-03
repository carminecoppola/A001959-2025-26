// Esercizio P2: implementazione semplice di una DiffUtil.Callback.
// DiffUtil serve a confrontare due liste e aggiornare solo gli elementi cambiati,
// evitando di ridisegnare tutta la RecyclerView.

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

    // Qui confrontiamo l'identita dell'elemento, quindi capiamo se stiamo parlando
    // dello stesso record anche se il contenuto e cambiato.
    fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    // Qui confrontiamo il contenuto completo per capire se i dati sono davvero identici.
    fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    // Metodo didattico per vedere il tipo di differenze tra le due liste.
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
                        changes.add("Elemento diverso in posizione $index")
                    } else if (!areContentsTheSame(index, index)) {
                        changes.add("Aggiornato: ${oldItem.title}")
                    }
                }
            }
        }

        return changes
    }
}

// Caso d'uso di base: confrontiamo una lista vecchia con una nuova.
fun demoP2DiffUtilCallback(): List<String> {
    val oldList = listOf(
        LessonItemP2(1, "Apple", "Frutto rosso"),
        LessonItemP2(2, "Banana", "Frutto giallo")
    )

    val newList = listOf(
        LessonItemP2(1, "Apple", "Frutto rosso e dolce"),
        LessonItemP2(3, "Cherry", "Frutto piccolo")
    )

    val callback = SimpleDiffUtilCallbackP2(oldList, newList)
    return callback.calculateChanges()
}

fun main() {
    val results = demoP2DiffUtilCallback()
    results.forEach { println(it) }
}