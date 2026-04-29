// Esercizio P1: implementazione semplice di un adapter per RecyclerView.
// L'obiettivo e mostrare il flusso base: creare una ViewHolder, collegare i dati
// e restituire la rappresentazione dell'elemento da mostrare in lista.

data class LessonItemP1(
    val id: Int,
    val title: String,
    val description: String
)

class SimpleRecyclerViewAdapterP1(
    private val items: List<LessonItemP1>
) {
    // Questa classe simula una ViewHolder di RecyclerView.
    // In Android la ViewHolder contiene i riferimenti alle viste dell'elemento.
    class ViewHolder {
        private var currentItem: LessonItemP1? = null

        // Questa funzione collega i dati al singolo elemento della lista.
        fun bind(item: LessonItemP1): String {
            currentItem = item
            return "[${item.id}] ${item.title} - ${item.description}"
        }

        // Restituiamo il contenuto attualmente associato alla ViewHolder.
        fun getBoundItem(): LessonItemP1? = currentItem
    }

    // Questa funzione crea un oggetto ViewHolder e lo associa a un layout specifico
    // per ogni elemento della lista.
    fun onCreateViewHolder(): ViewHolder = ViewHolder()

    // Questa funzione prende i dati della posizione richiesta e li passa alla ViewHolder.
    // In questo modo la vista mostra le informazioni corrette.
    fun onBindViewHolder(holder: ViewHolder, position: Int): String {
        val item = items[position]
        return holder.bind(item)
    }

    fun getItemCount(): Int = items.size

    // Metodo di supporto per vedere il risultato finale in modo semplice.
    fun renderAllItems(): List<String> {
        val renderedItems = mutableListOf<String>()

        for (position in items.indices) {
            val holder = onCreateViewHolder()
            renderedItems.add(onBindViewHolder(holder, position))
        }

        return renderedItems
    }
}

// Caso d'uso di base: costruiamo una lista e la facciamo renderizzare dall'adapter.
fun demoP1AdapterImplementation(): List<String> {
    val sampleItems = listOf(
        LessonItemP1(1, "Primo elemento", "Questo e il primo dato della lista"),
        LessonItemP1(2, "Secondo elemento", "Questo e il secondo dato della lista")
    )

    val adapter = SimpleRecyclerViewAdapterP1(sampleItems)
    return adapter.renderAllItems()
}