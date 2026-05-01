/*
Problem 2 - ViewBinding in Fragment

Obiettivo:
- Comprendere come usare ViewBinding nei Fragment evitando memory leak.
- Gestire correttamente il lifecycle della view del Fragment.

Spiegazione:
- In un Fragment si usa spesso una variabile nullable `_binding` e un getter non-null `binding`.
- Il binding viene inizializzato in `onCreateView` e usato mentre la view esiste.
- In `onDestroyView` è obbligatorio impostare `_binding = null` per evitare memory leak.
- Questo pattern evita di mantenere riferimenti alla view oltre il suo lifecycle.

Micro-esempio Android:
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

Edge cases / common mistakes:
- Dimenticare `_binding = null` in `onDestroyView` può causare memory leak.
- Usare `binding` fuori dal lifecycle della view può provocare crash.
- Se il Fragment resta vivo ma la view viene ricreata, il binding precedente non è più valido.
- Accedere alla UI prima di `onCreateView` o dopo `onDestroyView` è un errore.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P2_ViewBindingFragment.kt -include-runtime -d P2_ViewBindingFragment.jar
2. Esegui il programma:
    java -jar P2_ViewBindingFragment.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("ViewBinding in Fragment — solution")
}
