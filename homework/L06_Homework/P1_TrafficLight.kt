/*
Problem 1 - Traffic Light

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_TrafficLight.kt -include-runtime -d P1_TrafficLight.jar
2. Run the program:
   java -jar P1_TrafficLight.jar
*/

enum class TrafficLight {
    RED, GREEN, YELLOW;

    fun next() = when(this) {
        RED -> GREEN
        GREEN -> YELLOW
        YELLOW -> RED
    }

    fun duration() = when(this) {
        RED -> 30
        GREEN -> 25
        YELLOW -> 5
    }
}

fun main() {
    var light = TrafficLight.RED

    repeat(6) {
        println("$light: ${light.duration()}s")
        light = light.next()
    }

    println("\n")
    var light2 = TrafficLight.GREEN

    repeat(6) {
        println("$light2: ${light2.duration()}s")
        light2 = light2.next()
    }
}
