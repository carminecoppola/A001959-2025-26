/*
Problem 5 - Back stack flow

Obiettivo:
- Comprendere come gli Intent influenzano back stack e navigazione.
- Capire cosa succede quando si preme BACK tra più schermate.

Spiegazione:
- `startActivity()` aggiunge una nuova Activity sopra lo stack.
- Con il tasto BACK la Activity corrente viene rimossa e si torna a quella precedente.
- Flag come `FLAG_ACTIVITY_NEW_TASK` possono cambiare il comportamento dello stack.
- Anche i `launchMode` influiscono su come una Activity viene riutilizzata o ricreata.
- I principali launchMode sono: standard, singleTop, singleTask, singleInstance.

Micro-esempio Android:
MainActivity -> ListActivity -> DetailActivity
BACK: DetailActivity viene rimossa, torna ListActivity

Edge cases / common mistakes:
- Usare flag senza capirli può confondere il flusso di navigazione.
- Il back stack può comportarsi in modo diverso tra task differenti.
- `singleTask` e `singleInstance` cambiano drasticamente il riuso delle Activity.
- Una navigazione mal progettata può far perdere il contesto all'utente.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P5_BackStackFlow.kt -include-runtime -d P5_BackStackFlow.jar
2. Esegui il programma:
    java -jar P5_BackStackFlow.jar
3. Questo esercizio non richiede input interattivo.
*/

fun main() {
    println("Back stack flow — solution")
}
