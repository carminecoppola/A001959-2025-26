/*
Problem 5 - Coroutine Scope
Obiettivo:
- Lanciare 5 coroutine e attendere che tutte finiscano prima di terminare il programma.

Spiegazione codice:
- `runBlocking` crea lo scope di partenza.
- `coroutineScope` garantisce structured concurrency e attende tutte le coroutine figlie.
- `launch` avvia le unità di lavoro concorrenti.
- `delay` simula completamenti asincroni in tempi casuali.

Edge cases:
- Se una coroutine fallisce, lo scope può cancellare le altre.
- Se si usa `launch` senza uno scope che attende, il programma può terminare prima che finiscano.
- Il lavoro qui è concorrente ma non restituisce risultati, quindi `launch` è appropriato.

Come eseguirlo da terminale con coroutines:
1. Scaricare la libreria:
    curl -L https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.7.3/kotlinx-coroutines-core-jvm-1.7.3.jar -o coroutines.jar
2. Compilare:
    kotlinc P5_CoroutineScope.kt -cp coroutines.jar -include-runtime -d P5_CoroutineScope.jar
3. Eseguire:
    java -cp "P5_CoroutineScope.jar:coroutines.jar" P5_CoroutineScopeKt
*/

import kotlinx.coroutines.*

fun main() = runBlocking {
    coroutineScope {
        (1..5).forEach { id ->
            launch {
                delay((Math.random() * 500).toLong())
                println("Coroutine $id done")
            }
        }
    }

    println("All done")
}