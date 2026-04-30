

// Exercise 2 - Sealed Class - Use sealed class for API results. Show exhaustive when.

package l06.exercise2

// Sealed class: rappresenta un insieme CHIUSO di sottotipi
// Tutti i possibili stati sono definiti qui
sealed class ApiResult<out T> {

    // Caso SUCCESSO: contiene un dato di tipo generico T
    data class Success<T>(val data: T) : ApiResult<T>()

    // Caso ERRORE: contiene un messaggio di errore
    data class Error(val msg: String) : ApiResult<Nothing>()

    // Caso LOADING: nessun dato, solo stato
    object Loading : ApiResult<Nothing>()
}

// Funzione che gestisce il risultato API
fun handle(result: ApiResult<String>) {

    // when su sealed class → exhaustive automaticamente
    when (result) {

        // Caso successo → accedo al dato
        is ApiResult.Success -> {
            println("Got: ${result.data}")
        }

        // Caso errore → stampo messaggio
        is ApiResult.Error -> {
            println("Error: ${result.msg}")
        }

        // Caso loading → stato intermedio
        is ApiResult.Loading -> {
            println("Loading...")
        }
    }
    // NON serve else → tutti i casi sono coperti
}

fun main() {
    // Simulazione di risposta API
    val response = ApiResult.Success("Hello")

    // Gestione della risposta
    handle(response)
}