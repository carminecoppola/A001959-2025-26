/*
Problem 3 - Implicit intent examples

Obiettivo:
- Comprendere come usare implicit Intent per azioni gestite dal sistema o da altre app.
- Riconoscere gli use case comuni come browser, dialer e share.

Spiegazione:
- Un implicit Intent non specifica una classe target, ma un'azione e spesso un dato.
- Il sistema sceglie l'app più adatta a gestire l'Intent.
- È il meccanismo usato per aprire URL, fare chiamate o condividere contenuti.
- Prima di chiamare `startActivity` è buona pratica verificare `resolveActivity()`.

Micro-esempio Android:
val url = Uri.parse("https://developer.android.com")
val intent = Intent(Intent.ACTION_VIEW, url)
if (intent.resolveActivity(packageManager) != null) {
    startActivity(intent)
}

Edge cases / common mistakes:
- Se nessuna app gestisce l'Intent, l'avvio può fallire o causare crash se non controllato.
- Dati o action errati fanno scegliere un'app sbagliata o nessuna app.
- Per condividere contenuti complessi serve attenzione al formato e al tipo MIME.
- I flussi implicit intent dipendono dalle app installate sul device.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P3_ImplicitIntentExamples.kt -include-runtime -d P3_ImplicitIntentExamples.jar
2. Esegui il programma:
    java -jar P3_ImplicitIntentExamples.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Implicit intent examples — solution")
}
