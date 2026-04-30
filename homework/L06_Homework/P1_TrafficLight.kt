/*
Problem 1 - Traffic Light
Obiettivo:
- Creare enum TrafficLight con RED, YELLOW, GREEN, più next() e duration().

Spiegazione codice:
- L'enum TrafficLight definisce i tre stati del semaforo.
- La funzione next() usa when(this) per restituire lo stato successivo in modo ciclico.
- La funzione duration() restituisce la durata in secondi associata ad ogni stato.

Edge cases:
- L'uso di when su un enum è esaustivo: non è necessario includere un ramo else.
- Le durate sono valori fissi; cambiandole si modifica il comportamento ma non la struttura.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P1_TrafficLight.kt -include-runtime -d P1_TrafficLight.jar
2- Esegui il programma
    java -jar P1_TrafficLight.jar
3- Questo esercizio non richiede input
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
