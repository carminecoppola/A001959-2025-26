/*
Problem 4 - Back stack

Obiettivo:
- Comprendere come funziona il back stack dei Fragment.
- Distinguere tra aggiunta semplice e navigazione con storico.

Spiegazione:
- `FragmentManager` gestisce lo stack dei Fragment.
- `addToBackStack()` aggiunge la transazione allo storico, così BACK può tornare al Fragment precedente.
- `popBackStack()` rimuove l'ultimo elemento dello stack.
- Se non si usa il back stack, la navigazione sostituisce il Fragment senza possibilità di ritorno automatico.

Micro-esempio Android:
fragmentManager.beginTransaction()
    .replace(R.id.container, DetailFragment())
    .addToBackStack(null)
    .commit()

Edge cases / common mistakes:
- Dimenticare `addToBackStack()` rende il tasto BACK incoerente rispetto alle aspettative.
- Usare troppi replace senza capire lo stack può creare navigazione confusa.
- Il back stack del Fragment non coincide sempre con quello dell'Activity.
- Un `popBackStack()` mal posizionato può portare a schermi inattesi o stack vuoto.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P4_BackStack.kt -include-runtime -d P4_BackStack.jar
2. Esegui il programma:
    java -jar P4_BackStack.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Back stack — solution")
}
