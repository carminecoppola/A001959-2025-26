/*
Problem 1 - Explicit intent launch

Obiettivo:
- Comprendere come usare un explicit Intent per aprire una specifica Activity.
- Distinguere la navigazione interna da quella gestita dal sistema.

Spiegazione:
- Un explicit Intent specifica direttamente la Activity target.
- È il caso tipico di navigazione interna all'app.
- Il sistema non deve scegliere l'app corretta perché la destinazione è già nota.
- Questo approccio è semplice e sicuro quando si naviga tra schermate della stessa app.

Micro-esempio Android:
val intent = Intent(this, DetailActivity::class.java)
startActivity(intent)

Edge cases / common mistakes:
- Usare il nome Activity sbagliato porta a errore di compilazione o navigazione fallita.
- Non serve `resolveActivity()` per explicit Intent, perché la destinazione è già definita.
- `startActivityForResult` è deprecato e non dovrebbe essere usato per nuovi flussi.
- Se l'Activity non è dichiarata correttamente nel manifest, l'avvio può fallire.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P1_ExplicitIntentLaunch.kt -include-runtime -d P1_ExplicitIntentLaunch.jar
2. Esegui il programma:
    java -jar P1_ExplicitIntentLaunch.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Explicit intent launch — solution")
}
