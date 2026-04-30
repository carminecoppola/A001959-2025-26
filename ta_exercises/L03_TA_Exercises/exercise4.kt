

// Exercise 4 - Smart Cast - Show how Kotlin automatically casts after a null check.

fun printLength(text: String?) {
    // Il compilatore Kotlin tiene traccia dei controlli espliciti sul valore null.
    // All'interno del blocco 'if (text != null)', sa con certezza che
    // 'text' non può essere null, quindi lo tratta automaticamente come 'String'
    // (non più 'String?'). Questo meccanismo si chiama SMART CAST.
    if (text != null) {
        // Smart Cast attivo: text è String, possiamo accedere a .length
        // senza bisogno di '?.' o di un cast esplicito
        println("Length: ${text.length}")
    } else {
        println("text is null")
    }
}

fun main() {
    printLength("Kotlin")  // Output: Length: 6
    printLength(null)      // Output: text is null

    // 💡 Lo Smart Cast funziona anche con 'is', 'as?' e altri controlli.
    //    Abbiamo già visto un esempio nella Lezione 02 (Esercizio 5).
    //    Nota: lo Smart Cast NON funziona su 'var' se la variabile
    //    potrebbe essere modificata da un altro thread nel frattempo.
}