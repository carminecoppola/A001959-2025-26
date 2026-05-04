/*
Problem 4 - Observer Pattern

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_ObserverPattern.kt -include-runtime -d P4_ObserverPattern.jar
2. Run the program:
   java -jar P4_ObserverPattern.jar
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
