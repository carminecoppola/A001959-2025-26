

// Exercise 5 - let with Nullable - Use .let {} to execute a block only when value is non-null.

package l03.exercise5

fun main() {
    val email: String? = "alice@example.com"

    // '?.let { }' è l'idioma Kotlin per eseguire un blocco di codice
    // SOLO SE il valore non è null. È l'alternativa funzionale all'if-not-null.
    // 'addr' è il nome dato al valore non-null all'interno del blocco lambda
    // (se non specificato, si usa il nome implicito 'it')
    email?.let { addr ->
        println("Sending to: $addr")
        // 'substringAfter' restituisce la parte della stringa dopo il carattere dato
        println("Domain: ${addr.substringAfter('@')}")
    }
    // Output:
    // Sending to: alice@example.com
    // Domain: example.com

    val nullEmail: String? = null
    // Se il valore è null, il blocco 'let' non viene eseguito affatto
    nullEmail?.let { println("This won't print") }
    // non stampa nulla

    // Confronto tra i due approcci equivalenti:
    //    APPROCCIO IMPERATIVO:
    //    if (email != null) { println(email) }
    //
    //    APPROCCIO FUNZIONALE (idiomatico in Kotlin):
    //    email?.let { println(it) }
    //
    //    'let' è preferito quando il blocco contiene più istruzioni
    //    che operano tutte sullo stesso valore non-null.
}