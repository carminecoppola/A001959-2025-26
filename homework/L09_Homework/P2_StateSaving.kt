/*
Problem 2 - State Saving

Obiettivo:
- Comprendere come salvare e ripristinare lo stato di un'Activity.
- Evitare perdita di dati durante la rotazione o la distruzione inattesa.

Spiegazione:
- onSaveInstanceState(Bundle out) salva lo stato prima della distruzione
- I dati vengono salvati in un Bundle e passati a onCreate/onRestoreInstanceState
- Il Bundle è ideale per dati primitivi o piccoli oggetti serializzabili
- ViewModel è la soluzione preferita per dati complessi (sopravvive a rotazione e config changes)
- Bundle vs ViewModel:
  - Bundle: automatico ma limitato, usa onSaveInstanceState
  - ViewModel: manuale ma persistente, conserva dati tra rotazioni

Esempio di salvataggio in onSaveInstanceState:
out.putInt("count", 42)
out.putString("name", "User")

Esempio di ripristino in onCreate:
val savedCount = savedInstanceState?.getInt("count", 0) ?: 0

ViewModel best practice:
class MyViewModel : ViewModel() {
    var data: String = ""
}

Edge cases:
- onSaveInstanceState non è garantito se l'app viene terminata forzatamente
- Bundle ha limite di memoria (~1MB)
- Dati nel Bundle vengono persi se il processo viene ucciso dal sistema
- ViewModel sopravvive a rotazione ma non a navigazione back (onDestroy definitivo)
- Confusione tra onSaveInstanceState e onPause

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P2_StateSaving.kt -include-runtime -d P2_StateSaving.jar
2. Esegui il programma:
    java -jar P2_StateSaving.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("State saving — solution")
}
