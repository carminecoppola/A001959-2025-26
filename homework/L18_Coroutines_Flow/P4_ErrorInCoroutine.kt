// L18 - P4: gestione degli errori nelle coroutine con try-catch.
// L'obiettivo e intercettare gli errori durante un'operazione asincrona simulata.

sealed class CoroutineResultP4<out T> {
    data class Success<T>(val data: T) : CoroutineResultP4<T>()
    data class Failure(val message: String) : CoroutineResultP4<Nothing>()
}

class CoroutineErrorHandlerSimulatorP4 {
    fun runOperation(shouldFail: Boolean): CoroutineResultP4<String> {
        return try {
            if (shouldFail) {
                throw RuntimeException("Errore nella coroutine simulata")
            }
            CoroutineResultP4.Success("Operazione completata")
        } catch (exception: Exception) {
            CoroutineResultP4.Failure(exception.message ?: "Errore sconosciuto")
        }
    }
}

// Caso d'uso di base: vediamo come si comporta il gestore errori.
fun demoL18P4ErrorInCoroutine(): List<CoroutineResultP4<String>> {
    val handler = CoroutineErrorHandlerSimulatorP4()
    return listOf(
        handler.runOperation(shouldFail = false),
        handler.runOperation(shouldFail = true)
    )
}

fun main() {
    println("=== Error in Coroutine ===")
    val results = demoL18P4ErrorInCoroutine()
    results.forEach { result ->
        when (result) {
            is CoroutineResultP4.Success -> println("✓ ${result.data}")
            is CoroutineResultP4.Failure -> println("✗ ${result.message}")
        }
    }
}