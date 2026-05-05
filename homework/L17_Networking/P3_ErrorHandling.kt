// L17 - P3: error handling in network calls.
// The idea is distinguere a response corretta da a failure.

sealed class NetworkResultP3<out T> {
    data class Success<T>(val data: T) : NetworkResultP3<T>()
    data class Error(val message: String) : NetworkResultP3<Nothing>()
}

class RetrofitCallSimulatorP3 {
    fun fetchData(shouldFail: Boolean): NetworkResultP3<String> {
        return try {
            if (shouldFail) {
                throw IllegalStateException("Errore di network simulato")
            }
            NetworkResultP3.Success("Risposta ricevuta dal is usedr")
        } catch (exception: Exception) {
            NetworkResultP3.Error(exception.message ?: "Errore sconosciuto")
        }
    }
}

// Basic use case: vediamo a success and a error.
fun demoL17P3ErrorHandling(): List<NetworkResultP3<String>> {
    val client = RetrofitCallSimulatorP3()
    return listOf(
        client.fetchData(shouldFail = false),
        client.fetchData(shouldFail = true)
    )
}

fun main() {
    println("=== Error Handling ===")
    val results = demoL17P3ErrorHandling()
    results.forEach { result ->
        when (result) {
            is NetworkResultP3.Success -> println("✓ Success: ${result.data}")
            is NetworkResultP3.Error -> println("✗ Error: ${result.message}")
        }
    }
}