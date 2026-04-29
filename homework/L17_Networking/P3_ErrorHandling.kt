// L17 - P3: gestione degli errori nelle chiamate di rete.
// L'idea e distinguere una risposta corretta da un fallimento.

sealed class NetworkResultP3<out T> {
    data class Success<T>(val data: T) : NetworkResultP3<T>()
    data class Error(val message: String) : NetworkResultP3<Nothing>()
}

class RetrofitCallSimulatorP3 {
    fun fetchData(shouldFail: Boolean): NetworkResultP3<String> {
        return try {
            if (shouldFail) {
                throw IllegalStateException("Errore di rete simulato")
            }
            NetworkResultP3.Success("Risposta ricevuta dal server")
        } catch (exception: Exception) {
            NetworkResultP3.Error(exception.message ?: "Errore sconosciuto")
        }
    }
}

// Caso d'uso di base: vediamo un successo e un errore.
fun demoL17P3ErrorHandling(): List<NetworkResultP3<String>> {
    val client = RetrofitCallSimulatorP3()
    return listOf(
        client.fetchData(shouldFail = false),
        client.fetchData(shouldFail = true)
    )
}