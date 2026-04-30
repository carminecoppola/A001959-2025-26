

// Exercise 1 - Spot the NPE (NullPointerException)

// In Java, chiamare un metodo su un riferimento null causa un
// NullPointerException (NPE) a RUNTIME, che può far crashare l'applicazione se non gestito correttamente.
// Kotlin risolve questo problema a livello di SISTEMA DEI TIPI:
//  - una variabile non può essere null a meno che il suo tipo non lo dichiari
//  - esplicitamente con il simbolo '?' (es. String vs String?).

// Questa funzione restituisce String? (nullable): può restituire null
fun getUser(): String? = null

fun main() {
    val user = getUser()  // tipo inferito: String? (potenzialmente null)

    // Questo causerebbe un errore di compilazione in Kotlin:
    // val length = user.length  // Error: Only safe (?.) or non-null asserted (!!.) calls allowed

    // Soluzione con Safe Call '?.' ed Elvis '?:':
    // '?.' → chiama .length SOLO se user non è null, altrimenti restituisce null
    // '?:' → se l'espressione a sinistra è null, usa il valore di default a destra
    val length = user?.length ?: 0

    println("Length: $length")  // Output: Length: 0
    // (user è null → user?.length è null → l'Elvis restituisce 0)
}