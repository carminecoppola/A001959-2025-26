/*
Problem 4 - Flow Transform
Obiettivo:
- Creare un `Flow` da 1 a 10, filtrare i pari, raddoppiare i valori e prendere i primi 3 risultati.

Spiegazione codice:
- `asFlow` trasforma la range in flusso.
- `onEach` aggiunge una sospensione per simulare produzione asincrona.
- `filter`, `map`, `take` costruiscono la pipeline.
- `collect` consuma e stampa ogni elemento finale.
- `runBlocking` rende eseguibile il flusso da `main`.

Edge cases:
- `take(3)` limita l'output e interrompe la raccolta dopo tre elementi.
- Senza `take`, il flusso continuerebbe fino alla fine.
- Se una trasformazione lancia eccezione, l'intera pipeline fallisce.
- Se il flusso non viene `collect`-ato non produce valori.

Come eseguirlo da terminale con coroutines:
1. Scaricare la libreria:
   curl -L https://repo1.maven.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.7.3/kotlinx-coroutines-core-jvm-1.7.3.jar -o coroutines.jar
2. Compilare:
   kotlinc P4_FlowTransform.kt -cp coroutines.jar -include-runtime -d P4_FlowTransform.jar
3. Eseguire:
   java -cp "P4_FlowTransform.jar:coroutines.jar" P4_FlowTransformKt

*/

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    (1..10).asFlow()
        .onEach { delay(100) }
        .filter { it % 2 == 0 }
        .map { it * 2 }
        .take(3)
        .collect { println(it) }
}