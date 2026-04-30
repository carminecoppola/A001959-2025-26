/*
Problem 5 - requireNotNull Practice
Obiettivo:
- Validare una Map<String, String?> leggendo host, port e apiKey.
- Se uno di questi valori è null, generare un errore con messaggio chiaro.

Spiegazione codice:
- requireNotNull controlla che il valore letto dalla mappa non sia null.
- Se il valore è null, viene lanciata un'eccezione con il messaggio fornito nel lambda.
- Questo è utile quando un campo è obbligatorio e non si vuole proseguire con dati incompleti.
- Nel main viene passata una configurazione valida per mostrare il caso corretto.

Edge cases:
- host null: viene generato l'errore "host is required".
- port null: viene generato l'errore "port is required".
- apiKey null: viene generato l'errore "apiKey is required".
- La funzione accetta String? perché la mappa può contenere valori mancanti.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P5_RequireNotNullPractice.kt -include-runtime -d P5_RequireNotNullPractice.jar
2- Esegui il programma
    java -jar P5_RequireNotNullPractice.jar
3- Questo esercizio non richiede input
*/

fun validateConfig(config: Map<String, String?>) {
    val host   = requireNotNull(config["host"])   { "host is required" }
    val port   = requireNotNull(config["port"])   { "port is required" }
    val apiKey = requireNotNull(config["apiKey"]) { "apiKey is required" }

    println("Config OK: $host:$port key=$apiKey")
}

fun main() {
    validateConfig(mapOf("host" to "api.example.com", "port" to "443", "apiKey" to "secret"))
}