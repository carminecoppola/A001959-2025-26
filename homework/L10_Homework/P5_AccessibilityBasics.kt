/*
Problem 5 - Accessibility basics

Obiettivo:
- Comprendere le basi dell'accessibilità nelle UI Android.
- Progettare interfacce utilizzabili anche con screen reader e font scaling.

Spiegazione:
- Una UI accessibile è leggibile, navigabile e comprensibile per più utenti.
- Il testo deve usare `sp` per rispettare le preferenze di dimensione font.
- Immagini e pulsanti iconici devono avere `contentDescription`.
- I campi input dovrebbero avere label o hint chiari.
- Evitare contrasti bassi, testi troppo piccoli e interazioni ambigue.
- L'accessibilità non è opzionale: migliora qualità e usabilità generale.

Micro-esempio XML:
<ImageView
    android:id="@+id/imgProfile"
    android:contentDescription="Profile picture" />

Micro-esempio utile:
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="18sp"
    android:text="Accessible text" />

Edge cases / common mistakes:
- `contentDescription` mancante rende la View poco leggibile dagli screen reader.
- Usare solo colore o icone senza testo può confondere chi usa assistive tech.
- Testi troppo piccoli ignorano il font scaling dell'utente.
- Label poco chiare o assenti rendono difficoltoso compilare form e login.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P5_AccessibilityBasics.kt -include-runtime -d P5_AccessibilityBasics.jar
2. Esegui il programma:
    java -jar P5_AccessibilityBasics.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Accessibility basics — solution")
}
