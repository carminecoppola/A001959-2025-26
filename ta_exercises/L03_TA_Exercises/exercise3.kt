

// Exercise 3 - Elvis with Early Return - Use Elvis operator for early return pattern

// Pattern molto comune in Android: uscire subito da una funzione
// se un parametro nullable non è valido (guard clause).
fun processUser(name: String?) {
    // L'operatore Elvis '?:' non deve restituire per forza un valore:
    // può eseguire anche 'return', 'throw' o qualsiasi altra espressione.
    // Se 'name' è null → viene eseguito 'return' e la funzione termina subito.
    // Se 'name' non è null → viene assegnato a 'validName' come String (non nullable!)
    val validName = name ?: return

    // Da questo punto in poi, 'validName' è garantito non-null dal compilatore
    println("Processing: $validName")
}

fun main() {
    processUser(null)   // name è null → return immediato → non stampa nulla
    processUser("Bob")  // name non è null → Output: Processing: Bob

    // 💡 Questo pattern prende il nome di "Early Return" o "Guard Clause"
    //    e rende il codice più leggibile evitando if annidati a cascata.
}