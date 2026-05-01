/*
Problem 3 - Timeout
Obiettivo:
- Limitare il tempo di esecuzione di un'operazione lenta usando `withTimeout`.

Spiegazione codice:
- `runBlocking` avvia il programma.
- `withTimeout` cancella il blocco se supera la soglia.
- `delay` simula un lavoro lento.
- Il `try/catch` intercetta `TimeoutCancellationException` e converte il timeout in un messaggio leggibile.

Edge cases:
- Se il timeout è troppo basso, il blocco fallisce anche se il lavoro sarebbe stato valido.
- Le eccezioni diverse dal timeout non vengono trasformate in "Timed out!".
- La cancellazione è cooperativa, quindi il codice sospeso deve rispettarla per terminare correttamente.

Come eseguirlo da terminale con coroutines:
1. Scaricare la libreria:
    curl -L https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.7.3/kotlinx-coroutines-core-jvm-1.7.3.jar -o coroutines.jar
2. Compilare:
    kotlinc P3_Timeout.kt -cp coroutines.jar -include-runtime -d P3_Timeout.jar
3. Eseguire:
    java -cp "P3_Timeout.jar:coroutines.jar" P3_TimeoutKt
*/

import kotlinx.coroutines.*

fun main() = runBlocking {
    val result = try {
        withTimeout(2000L) {
            delay(3000L)
            "Done"
        }
    } catch (e: TimeoutCancellationException) {
        "Timed out!"
    }

    println(result)
}