

// Exercise 3 - Lambda Basics - Create lambdas for common math operations. Store them in variables.

fun main() {
    // Una LAMBDA è una funzione anonima che può essere assegnata a una variabile,
    // passata come argomento o restituita da un'altra funzione.
    // Sintassi: { parametri -> corpo }

    // Lambda con due parametri Int, restituisce Int
    val add = { a: Int, b: Int -> a + b }

    // Lambda con un parametro Int, restituisce Boolean
    val even = { n: Int -> n % 2 == 0 }

    // Quando la lambda ha UN SOLO parametro, possiamo usare il nome implicito 'it'
    // Il tipo è dichiarato esplicitamente: (String) -> String
    val shout: (String) -> String = { it.uppercase() + "!" }

    println(add(3, 4))      // Output: 7
    println(even(7))        // Output: false
    println(shout("kotlin")) // Output: KOTLIN!

    // I tipi funzione in Kotlin si scrivono come: (TipoParam) -> TipoRitorno
}