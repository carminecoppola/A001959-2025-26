/*
Problem 3 - View attributes

Obiettivo:
- Comprendere gli attributi principali delle View Android.
- Riconoscere i campi XML più usati per configurare componenti UI.

Spiegazione:
- Le View principali includono TextView, EditText, Button e ImageView.
- Gli attributi più comuni sono `android:id`, `android:layout_width`, `android:layout_height`, `android:text`, `android:textSize`, `android:hint` e `android:contentDescription`.
- `android:id` serve per referenziare la View nel codice o nel binding.
- `layout_width` e `layout_height` definiscono la dimensione della View.
- `text`, `hint` e `contentDescription` migliorano usabilità e accessibilità.

Micro-esempio XML:
<Button
    android:id="@+id/btnLogin"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Log In" />

Micro-esempio utile:
<EditText
    android:id="@+id/etEmail"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter email" />

Edge cases / common mistakes:
- Un id mancante rende difficile accedere alla View da Kotlin o ViewBinding.
- Testo troppo piccolo o assente peggiora usabilità e accessibilità.
- Dimenticare `contentDescription` per immagini o pulsanti iconici penalizza gli screen reader.
- Width/height incoerenti possono comprimere o espandere troppo i componenti.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P3_ViewAttributes.kt -include-runtime -d P3_ViewAttributes.jar
2. Esegui il programma:
    java -jar P3_ViewAttributes.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("View attributes — solution")
}
