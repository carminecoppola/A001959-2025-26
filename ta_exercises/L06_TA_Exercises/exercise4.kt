

// Exercise 4 - Map Operations - Group words by their first letter.

package l06.exercise4   

fun main() {

    // Lista di parole
    val words = listOf(
        "apple", "banana", "avocado",
        "cherry", "apricot", "blueberry"
    )

    // groupBy crea una mappa:
    // chiave = prima lettera
    // valore = lista di parole con quella lettera
    val grouped: Map<Char, List<String>> =
        words.groupBy { word ->
            word[0] // prima lettera della parola
        }

    // Iterazione sulla mappa
    grouped.forEach { (letter, list) ->

        // "letter" = chiave
        // "list" = lista di parole associate
        println("$letter: $list")
    }
}