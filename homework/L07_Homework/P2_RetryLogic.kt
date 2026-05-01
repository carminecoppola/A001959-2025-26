/*
Problem 2 - Retry Logic
Obiettivo:
- Scrivere una funzione suspend `withRetry` che ritenta un blocco più volte prima di fallire definitivamente.

Spiegazione codice:
- `withRetry` incapsula la gestione errori e memorizza l'ultima eccezione.
- Il `repeat(times)` controlla i tentativi.
- `runBlocking` permette di chiamare la `suspend function` da `main`.
- Il blocco di esempio simula un errore che si risolve dopo alcuni tentativi.

Edge cases:
- `times <= 0` porterebbe a nessun tentativo utile e poi a fallimento con `lastException!!`.
- Un retry infinito sarebbe pericoloso perché può bloccare risorse e mascherare errori.
- Se il blocco lancia eccezioni diverse da `Exception` non vengono intercettate da questo codice.
- Se il blocco ha effetti collaterali, ogni retry li ripete.

Come eseguirlo da terminale con coroutines:
1. Scaricare la libreria:
    curl -L https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.7.3/kotlinx-coroutines-core-jvm-1.7.3.jar -o coroutines.jar
2. Compilare:
    kotlinc P2_RetryLogic.kt -cp coroutines.jar -include-runtime -d P2_RetryLogic.jar
3. Eseguire:
    java -cp "P2_RetryLogic.jar:coroutines.jar" P2_RetryLogicKt

*/

import kotlinx.coroutines.*

suspend fun <T> withRetry(times: Int, block: suspend () -> T): T {
    var lastException: Exception? = null

    repeat(times) { attempt ->
        try {
            return block()
        } catch (e: Exception) {
            lastException = e
            println("Attempt ${attempt + 1} failed")
        }
    }

    throw lastException!!
}

fun main() = runBlocking {
    var count = 0

    val result = withRetry(3) {
        if (count++ < 2) throw Exception("Not yet")
        else "Success!"
    }

    println(result)
}