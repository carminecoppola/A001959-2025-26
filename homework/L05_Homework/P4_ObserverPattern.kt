/*
Problem 4 - Observer Pattern
Obiettivo:
- Creare EventBus con subscribe(listener) e publish(event).

Spiegazione codice:
- EventBus mantiene una lista di listener come lambda (String) -> Unit.
- subscribe aggiunge un listener, publish invoca ogni listener con l'evento.
- Questo è un semplice esempio di pattern observer usando funzioni come callback.

Edge cases:
- Nessun listener: publish non fa nulla.
- Listener che lancia eccezioni interromperebbe la notifica degli altri listener in questa semplice implementazione.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P4_ObserverPattern.kt -include-runtime -d P4_ObserverPattern.jar
2- Esegui il programma
    java -jar P4_ObserverPattern.jar
3- Questo esercizio non richiede input
*/

class EventBus {
    private val listeners = mutableListOf<(String) -> Unit>()

    fun subscribe(listener: (String) -> Unit) {
        listeners.add(listener)
    }

    fun publish(event: String) {
        listeners.forEach { it(event) }
    }
}

fun main() {
    val bus = EventBus()

    bus.subscribe { println("Listener1: $it") }
    bus.subscribe { println("Listener2: ${it.uppercase()}") }

    bus.publish("user_login")
}
