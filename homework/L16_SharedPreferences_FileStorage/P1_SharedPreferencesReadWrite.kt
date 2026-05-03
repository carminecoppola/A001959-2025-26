// L16 - P1: lettura e scrittura con SharedPreferences.
// Questo esempio mostra l'idea base: salvare una coppia chiave/valore e poi leggerla.

class SharedPreferencesSimulatorP1 {
    private val storage = mutableMapOf<String, String>()

    // Salviamo un valore di testo in memoria.
    fun putString(key: String, value: String) {
        storage[key] = value
    }

    // Leggiamo un valore di testo, oppure restituiamo un valore di default.
    fun getString(key: String, defaultValue: String = ""): String {
        return storage[key] ?: defaultValue
    }

    // Questo metodo mostra il flusso completo di scrittura e lettura.
    fun saveAndReadExample(): String {
        putString("username", "student")
        return getString("username")
    }
}

// Caso d'uso di base: simuliamo il salvataggio di un nome utente.
fun demoL16P1SharedPreferencesReadWrite(): String {
    return SharedPreferencesSimulatorP1().saveAndReadExample()
}

fun main() {
    println("=== SharedPreferences Read/Write ===")
    println(demoL16P1SharedPreferencesReadWrite())
}