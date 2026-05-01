/*
Problem 1 - Fragment lifecycle order

Obiettivo:
- Comprendere l'ordine del lifecycle di un Fragment.
- Distinguere il lifecycle del Fragment da quello di una Activity.

Spiegazione:
- L'ordine tipico di un Fragment è:
  onAttach → onCreate → onCreateView → onViewCreated → onStart → onResume → onPause → onStop → onDestroyView → onDestroy → onDetach
- `onCreateView` crea la view del Fragment, mentre `onViewCreated` è il punto giusto per inizializzare la UI.
- `onDestroyView` è diverso da `onDestroy`: la view può essere distrutta prima del Fragment stesso.
- Rispetto a un'Activity, il Fragment ha un lifecycle della view separato e più delicato.

Micro-esempio Android:
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // setup UI here
}

Edge cases / common mistakes:
- Confondere `onDestroyView` con `onDestroy` può causare bug nella gestione della UI.
- Usare oggetti della view dopo `onDestroyView` porta a crash o memory leak.
- Il Fragment non deve essere trattato come una semplice Activity: la view ha un lifecycle proprio.
- Se il Fragment è staccato dall'Activity, il suo contesto non è più valido.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P1_FragmentLifecycleOrder.kt -include-runtime -d P1_FragmentLifecycleOrder.jar
2. Esegui il programma:
    java -jar P1_FragmentLifecycleOrder.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Fragment lifecycle order — solution")
}
