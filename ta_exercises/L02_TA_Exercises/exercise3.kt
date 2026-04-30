

// Exercise 3 - Range Loops - Print multiplication table for 5 using for with a range

package l02.exercise3

fun main() {
    // In Kotlin i range si creano con l'operatore '..' (es. 1..10)
    // e includono sia il limite inferiore che quello superiore (range chiuso)
    for (i in 1..10) {
        println("5 x $i = ${5 * i}")
    }
    // Output: 5 x 1 = 5
    //         5 x 2 = 10
    //         ...
    //         5 x 10 = 50

    // Varianti utili dei range:
    // until  → range aperto a destra:  1 until 10  =  1..9
    // downTo → range decrescente:       10 downTo 1
    // step   → passo personalizzato:   1..10 step 2  → 1, 3, 5, 7, 9
}