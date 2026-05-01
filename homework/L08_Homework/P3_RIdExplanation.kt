/*
Problem 3 - R.id Explanation

Obiettivo:
- Comprendere il significato di R.id e il collegamento tra XML e codice Kotlin.

Spiegazione:
- La classe R è generata automaticamente da Android.
- Contiene riferimenti alle risorse del progetto: layout, stringhe, immagini e id.
- R.id permette di identificare elementi dichiarati nel layout XML.
- Con ViewBinding si può accedere agli elementi in modo type-safe.

Esempio XML:

<Button
    android:id="@+id/btnGreet"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Greet" />

Esempio Kotlin con ViewBinding:

binding.btnGreet.setOnClickListener {
    binding.tvMessage.text = "Hello, Android!"
}

Edge cases:
- Non ci sono input.
- In un progetto reale, un id mancante o rinominato può causare errori di riferimento.

Come eseguirlo:
kotlinc P3_RIdExplanation.kt -include-runtime -d P3_RIdExplanation.jar && java -jar P3_RIdExplanation.jar
*/

fun main() {
    println("R.id explanation — solution")
}