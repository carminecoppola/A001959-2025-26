/*
Problem 3 - Communication pattern

Obiettivo:
- Comprendere come comunicano Fragment tra loro o con Activity.
- Scegliere il pattern giusto in base al tipo di relazione.

Spiegazione:
- Fragment → Activity: si può usare un'interfaccia oppure un shared ViewModel.
- Fragment → Fragment: spesso si usa un ViewModel condiviso o Navigation arguments.
- `activityViewModels()` è utile quando più Fragment devono condividere lo stesso stato.
- Le communication patterns moderne preferiscono ViewModel e Navigation args rispetto ai callback fragili.

Micro-esempio Android:
private val viewModel: SharedViewModel by activityViewModels()

// oppure
// fragmentA passa dati a fragmentB tramite navArgs o shared ViewModel

Edge cases / common mistakes:
- Usare un'interfaccia senza verificare che Activity la implementi può causare crash.
- Dimenticare il lifecycle corretto del ViewModel può far perdere lo stato condiviso.
- Passare dati complessi con stringhe manuali invece che con un modello tipizzato è fragile.
- Usare un Fragment senza Activity ospite rende impossibile la comunicazione classica.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P3_CommunicationPattern.kt -include-runtime -d P3_CommunicationPattern.jar
2. Esegui il programma:
    java -jar P3_CommunicationPattern.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Communication pattern — solution")
}
