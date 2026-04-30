

// Exercise 4 - Higher-Order Functions - Write a function that takes a list and a transformation lambda.

// Una HIGHER-ORDER FUNCTION è una funzione che:
//  - accetta altre funzioni come parametri, oppure
//  - restituisce una funzione come risultato (o entrambe le cose)
// Questo è il fondamento della programmazione funzionale in Kotlin.

// <T, R> sono i GENERICS: T è il tipo degli elementi della lista di input,
// R è il tipo degli elementi della lista di output.
// 'fn: (T) -> R' è un parametro di tipo funzione: accetta un T e restituisce un R.
fun <T, R> transform(list: List<T>, fn: (T) -> R): List<R> =
    list.map(fn)  // 'map' applica 'fn' a ogni elemento e raccoglie i risultati

fun main() {
    val nums = listOf(1, 2, 3, 4, 5)

    // Chiamata con lambda TRAILING: quando l'ultimo argomento è una lambda,
    // può essere spostata fuori dalle parentesi (trailing lambda syntax)
    println(transform(nums) { it * it })
    // Output: [1, 4, 9, 16, 25]  (quadrati)

    println(transform(nums) { "item$it" })
    // Output: [item1, item2, item3, item4, item5]  (conversione a String)

    // 💡 La trailing lambda syntax è molto usata nelle API Android e Jetpack Compose.
    //    Esempio reale: button.setOnClickListener { /* codice */ }
    //    Qui il blocco { } è in realtà una lambda passata come argomento!
}