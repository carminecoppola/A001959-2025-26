

// Exercise 2 - When expression - Write a when expression that maps HTTP status codes to descriptions

// 'when' è l'equivalente potenziato dello 'switch' di Java/C,
// ma molto più espressivo: può confrontare valori, range, tipi e altro.
// Anche 'when', come 'if', è un'espressione e può restituire un valore.
fun describe(code: Int) = when (code) {
    200          -> "OK"
    404          -> "Not Found"
    401          -> "Unauthorized"
    500          -> "Server Error"

    // Con 'in' possiamo verificare se il valore appartiene a un range
    in 400..499  -> "Client Error"

    // 'else' è il ramo di default, obbligatorio quando 'when' è un'espressione
    else         -> "Unknown ($code)"
}

fun main() {
    println(describe(200))  // Output: OK
    println(describe(418))  // Output: Client Error
    // 418 rientra nel range 400..499, anche se non è esplicitamente elencato
}