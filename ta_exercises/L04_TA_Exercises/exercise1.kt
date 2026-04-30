

// Exercise 1 - Default Parameters - Create a sendEmail function with default values. Call it different ways.

// In Kotlin è possibile assegnare valori di default ai parametri di una funzione.
// Questo elimina la necessità di creare multiple versioni sovraccaricate (overload)
// della stessa funzione, come invece si fa comunemente in Java.
fun sendEmail(
    to: String,                  // parametro obbligatorio (nessun default)
    subject: String = "No subject",  // parametro opzionale con default
    body: String? = "",               // parametro opzionale con default
    html: Boolean = false            // parametro opzionale con default
) {
    println("To: $to | $subject | $body | html=$html")
}

fun main() {
    // Chiamata con solo il parametro obbligatorio → gli altri usano i default
    sendEmail("a@b.com")
    // Output: To: a@b.com | No subject | html=false

    // Chiamata con i primi due parametri in ordine posizionale
    sendEmail("a@b.com", "Hello")
    // Output: To: a@b.com | Hello | html=false

    // NAMED ARGUMENTS: con i parametri nominati possiamo passare solo
    // quelli che ci interessano, in qualsiasi ordine, saltando gli altri
    sendEmail("a@b.com", html = true)
    // Output: To: a@b.com | No subject | html=true

    // Tutti i parametri nominati, in ordine diverso da quello dichiarato
    sendEmail(to = "b@c.com", body = "Hi", subject = "Test")
    // Output: To: b@c.com | Test | html=false

    // I Named Arguments migliorano molto la leggibilità del codice,
    // specialmente quando una funzione ha molti parametri booleani:
    // sendEmail("x@y.com", html = true) è più chiaro di sendEmail("x@y.com", "", "", true)
}