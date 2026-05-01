/*
Problem 1 - ConstraintLayout XML

Obiettivo:
- Comprendere la struttura XML di un ConstraintLayout e l'uso dei vincoli.
- Capire perché Android UI è un albero di View e ViewGroup.

Spiegazione:
- Android UI è composta da View e ViewGroup organizzate ad albero.
- ConstraintLayout è un root flat e performante perché riduce i nesting inutili.
- Ogni View dovrebbe avere almeno un vincolo orizzontale e uno verticale.
- `0dp` in un layout con vincoli significa `match_constraint`, cioè la dimensione viene risolta dai constraint.
- Usare vincoli corretti evita posizionamenti ambigui e layout instabili.

Micro-esempio XML:
<TextView
    android:id="@+id/tvTitle"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />

Edge cases / common mistakes:
- Vincoli mancanti possono lasciare la View in alto a sinistra, cioè a (0,0).
- Usare `wrap_content` quando si vuole riempire lo spazio disponibile può produrre risultati diversi da `0dp`.
- Vincoli contraddittori o incompleti possono portare a layout non deterministici.
- Dimenticare il prefisso `app:` per i constraint impedisce all'XML di funzionare come previsto.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P1_ConstraintLayoutXML.kt -include-runtime -d P1_ConstraintLayoutXML.jar
2. Esegui il programma:
    java -jar P1_ConstraintLayoutXML.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("ConstraintLayout XML — solution")
}
