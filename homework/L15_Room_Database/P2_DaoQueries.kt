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
}

// Caso d'uso di base: eseguiamo una query e aggiungiamo un nuovo record.
fun demoP2DaoQueries(): List<String> {
    val dao = NoteDaoSimulatorP2(
        mutableListOf(
            NoteEntityP2(1, "Prima nota"),
            NoteEntityP2(2, "Seconda nota")
        )
    )

    dao.insert(NoteEntityP2(3, "Terza nota"))

    return dao.getAllNotes().map { note -> "${note.id}: ${note.text}" }
}