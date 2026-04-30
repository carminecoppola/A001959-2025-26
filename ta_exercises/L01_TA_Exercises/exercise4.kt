

// Exercise 4 - String Templates

fun main() {
    val a = 10
    val b = 3

    // Le String Template supportano due sintassi:
    //  1. $variabile      → inserisce direttamente il valore di una variabile
    //  2. ${espressione}  → valuta un'espressione arbitraria e ne inserisce il risultato

    println("$a divided by $b is ${a / b} remainder ${a % b}")
    // Output: 10 divided by 3 is 3 remainder 1

    // 💡 Nota: la divisione tra Int produce sempre un Int (divisione intera)
    //    Per ottenere un risultato decimale almeno uno dei due operandi
    //    deve essere Double: es. a.toDouble() / b → 3.3333
}