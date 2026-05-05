// Exercise P2: query DAO in stile Room.
// Un DAO espone operazioni for leggere and scrivere i data in modo organizzato.

data class NoteEntityP2(
    val id: Int,
    val text: String
)

class NoteDaoSimulatorP2(
    private val notess: MutableList<NoteEntityP2>
) {
    fun getAllNotes(): List<NoteEntityP2> = notess.toList()

    fun findById(id: Int): NoteEntityP2? = notess.firstOrNull { notes -> notes.id == id }

    fun insert(notes: NoteEntityP2) {
        notess.add(notes)
    }

    fun update(notes: NoteEntityP2) {
        val index = notess.indexOfFirst { it.id == notes.id }
        if (index != -1) {
            notess[index] = notes
        }
    }

    fun delete(id: Int) {
        notess.removeAll { it.id == id }
    }
}

// Basic use case: we run all CRUD operations (Create, Read, Update, Delete).
fun demoP2DaoQueries(): List<String> {
    val dao = NoteDaoSimulatorP2(
        mutableListOf(
            NoteEntityP2(1, "Prima nota"),
            NoteEntityP2(2, "Seconda nota")
        )
    )

    val output = mutableListOf<String>()

    // READ: we read all notess
    output.add("--- READ (All notess) ---")
    dao.getAllNotes().forEach { notes -> output.add("${notes.id}: ${notes.text}") }

    // CREATE: we insert a nuova nota
    output.add("\n--- CREATE (Inserisci nota 3) ---")
    dao.insert(NoteEntityP2(3, "Terza nota"))
    dao.getAllNotes().forEach { notes -> output.add("${notes.id}: ${notes.text}") }

    // UPDATE: we update a nota
    output.add("\n--- UPDATE (Aggiorna nota 2) ---")
    dao.update(NoteEntityP2(2, "Seconda nota MODIFICATA"))
    dao.getAllNotes().forEach { notes -> output.add("${notes.id}: ${notes.text}") }

    // DELETE: we delete a nota
    output.add("\n--- DELETE (Elimina nota 1) ---")
    dao.delete(1)
    dao.getAllNotes().forEach { notes -> output.add("${notes.id}: ${notes.text}") }

    return output
}

fun main() {
    val results = demoP2DaoQueries()
    results.forEach { println(it) }
}