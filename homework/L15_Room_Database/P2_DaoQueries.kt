// Esercizio P2: query DAO in stile Room.
// Un DAO espone operazioni per leggere e scrivere i dati in modo organizzato.

data class NoteEntityP2(
    val id: Int,
    val text: String
)

class NoteDaoSimulatorP2(
    private val notes: MutableList<NoteEntityP2>
) {
    fun getAllNotes(): List<NoteEntityP2> = notes.toList()

    fun findById(id: Int): NoteEntityP2? = notes.firstOrNull { note -> note.id == id }

    fun insert(note: NoteEntityP2) {
        notes.add(note)
    }

    fun update(note: NoteEntityP2) {
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
        }
    }

    fun delete(id: Int) {
        notes.removeAll { it.id == id }
    }
}

// Caso d'uso di base: eseguiamo tutte le operazioni CRUD (Create, Read, Update, Delete).
fun demoP2DaoQueries(): List<String> {
    val dao = NoteDaoSimulatorP2(
        mutableListOf(
            NoteEntityP2(1, "Prima nota"),
            NoteEntityP2(2, "Seconda nota")
        )
    )

    val output = mutableListOf<String>()

    // READ: leggiamo tutte le note
    output.add("--- READ (Tutte le note) ---")
    dao.getAllNotes().forEach { note -> output.add("${note.id}: ${note.text}") }

    // CREATE: inseriamo una nuova nota
    output.add("\n--- CREATE (Inserisci nota 3) ---")
    dao.insert(NoteEntityP2(3, "Terza nota"))
    dao.getAllNotes().forEach { note -> output.add("${note.id}: ${note.text}") }

    // UPDATE: aggiorniamo una nota
    output.add("\n--- UPDATE (Aggiorna nota 2) ---")
    dao.update(NoteEntityP2(2, "Seconda nota MODIFICATA"))
    dao.getAllNotes().forEach { note -> output.add("${note.id}: ${note.text}") }

    // DELETE: eliminiamo una nota
    output.add("\n--- DELETE (Elimina nota 1) ---")
    dao.delete(1)
    dao.getAllNotes().forEach { note -> output.add("${note.id}: ${note.text}") }

    return output
}

fun main() {
    val results = demoP2DaoQueries()
    results.forEach { println(it) }
}