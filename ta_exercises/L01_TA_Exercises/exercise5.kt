

// Exercise 5 - Reading Input

fun main() {
    // 'print' (senza 'ln') stampa senza andare a capo, così il cursore
    // rimane sulla stessa riga dell'input dell'utente
    print("Your name: ")

    // readLine() legge una riga dallo standard input e restituisce String?
    // Il tipo String? (con il '?') significa che può valere null
    // (es. se l'input è chiuso o vuoto)

    // L'operatore Elvis '?:' gestisce il caso null:
    // se readLine() restituisce null, si usa il valore di default "stranger"
    val name = readLine() ?: "stranger"

    println("Hello, $name! Welcome to Kotlin.")
    // Output (esempio): Hello, Mario! Welcome to Kotlin.

    // 💡 In Kotlin i tipi nullable (String?) e non-nullable (String) sono
    //    distinti a livello di sistema dei tipi: questo elimina i NullPointerException
    //    a runtime, uno dei problemi più comuni in Java
}