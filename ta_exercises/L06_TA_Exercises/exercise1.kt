

// Exercise 1 - Enum Class - Create a Direction enum with opposite() method.

package l06.exercise1

// Enum che rappresenta un insieme FINITO di valori costanti
enum class Direction {

    // Costanti dell'enum (istanze singleton)
    NORTH, SOUTH, EAST, WEST;

    // Metodo associato all'enum
    // Restituisce la direzione opposta rispetto a quella corrente
    fun opposite(): Direction =
        // "this" rappresenta l'istanza corrente dell'enum
        when (this) {

            // Quando la direzione è NORTH → ritorno SOUTH
            NORTH -> SOUTH

            // Quando la direzione è SOUTH → ritorno NORTH
            SOUTH -> NORTH

            // Quando la direzione è EAST → ritorno WEST
            EAST  -> WEST

            // Quando la direzione è WEST → ritorno EAST
            WEST  -> EAST
        }
        // NOTA: non serve "else" perché il when è EXHAUSTIVE
        // Kotlin sa che tutti i casi dell'enum sono coperti
}

fun main() {
    // Test del metodo
    val dir = Direction.NORTH

    // Chiamata del metodo opposite()
    val oppositeDir = dir.opposite()

    println(oppositeDir) // Output: SOUTH
}