// L16 - P3: setup di DataStore.
// Questo esempio mostra come potrebbe essere organizzato uno storage moderno a stato unico.

data class AppPreferencesP3(
    val darkModeEnabled: Boolean,
    val language: String
)

class DataStoreSimulatorP3(initialState: AppPreferencesP3) {
    private var state: AppPreferencesP3 = initialState

    fun read(): AppPreferencesP3 = state

    fun update(transform: (AppPreferencesP3) -> AppPreferencesP3) {
        state = transform(state)
    }
}

// Caso d'uso di base: cambiamo una preferenza e leggiamo il nuovo stato.
fun demoL16P3DataStoreSetup(): AppPreferencesP3 {
    val dataStore = DataStoreSimulatorP3(
        AppPreferencesP3(darkModeEnabled = false, language = "it")
    )

    dataStore.update { current -> current.copy(darkModeEnabled = true) }
    return dataStore.read()
}

fun main() {
    println("=== DataStore Setup ===")
    val result = demoL16P3DataStoreSetup()
    println("Dark mode: ${result.darkModeEnabled}, Language: ${result.language}")
}