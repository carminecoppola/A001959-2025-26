

// Exercise 5 - map / filter / reduce - Process a list of scores: 
// filter passing (>=50), double them, sum the result.

package l04.exercise5

fun main() {
    val scores = listOf(34, 67, 82, 45, 91, 55, 28)

    // Kotlin permette di concatenare operazioni sulle collezioni in stile
    // PIPELINE (o method chaining), rendendo il codice molto leggibile.
    // Ogni operazione riceve il risultato della precedente.
    val result = scores
        .filter { it >= 50 }        // 1) tieni solo i voti >= 50 → [67, 82, 91, 55]
        .map { it * 2 }             // 2) raddoppia ciascuno → [134, 164, 182, 110]
        .reduce { acc, n -> acc + n } // 3) somma tutti → 590

    // 'reduce' combina tutti gli elementi in un unico valore usando la lambda:
    //  - 'acc' è l'accumulatore (inizialmente il primo elemento)
    //  - 'n'   è l'elemento corrente
    // Passo per passo: 134 → 134+164=298 → 298+182=480 → 480+110=590

    println("Result: $result")  // Output: Result: 590

    // 💡 Queste tre operazioni (map, filter, reduce) sono i mattoni
    //    fondamentali della programmazione funzionale 
}