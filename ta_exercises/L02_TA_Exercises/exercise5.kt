

// Exercise 5 - Nested when with is

package l02.exercise5

// 'Any' è il supertipo di tutti i tipi in Kotlin (equivale a Object in Java).
// Usando 'is' possiamo verificare il tipo a runtime.
fun describe(obj: Any): String = when (obj) {

    // Dopo il controllo 'is Int', Kotlin fa lo SMART CAST automatico:
    // all'interno del ramo, 'obj' viene trattato come Int senza bisogno
    // di cast esplicito, e possiamo usare direttamente 'obj * 2'
    is Int     -> "Integer: ${obj * 2}"

    // Smart cast a String: 'obj.length' è accessibile senza cast manuale
    is String  -> "String of length ${obj.length}"

    // 'when' può contenere espressioni nei rami, non solo valori
    is Boolean -> if (obj) "true!" else "false!"

    else       -> "Unknown"
}

fun main() {
    println(describe(42))       // Output: Integer: 84
    println(describe("kotlin")) // Output: String of length 6
    println(describe(true))     // Output: true!

    // 💡 Lo Smart Cast è una delle funzionalità più apprezzate di Kotlin:
    //    il compilatore ricorda i controlli di tipo già effettuati e
    //    applica automaticamente il cast, eliminando il codice boilerplate
    //    tipico di Java (es. ((String) obj).length()).
}