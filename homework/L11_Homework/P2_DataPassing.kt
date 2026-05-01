/*
Problem 2 - Data passing

Obiettivo:
- Comprendere come passare dati tra Activity usando extras e Parcelable.
- Sapere quando usare primitive e quando usare oggetti complessi.

Spiegazione:
- Per dati semplici si usano gli extras dell'Intent.
- `putExtra` serve per passare primitive, stringhe e boolean.
- Il destinatario legge i valori con `getIntExtra`, `getStringExtra` e `getBooleanExtra`.
- Per oggetti complessi si usa `Parcelable` con `@Parcelize`.
- È una soluzione più efficiente di serializzazione generica per il mondo Android.

Micro-esempio Android:
intent.putExtra("EXTRA_USER_ID", 42)
val userId = intent.getIntExtra("EXTRA_USER_ID", -1)

@Parcelize
data class User(val id: Int, val name: String) : Parcelable

Edge cases / common mistakes:
- Chiavi extras scritte male fanno fallire la lettura dei dati.
- Dimenticare il default value nei getter può rendere il codice fragile.
- Oggetti troppo grandi negli Intent possono superare il limite Binder, circa 1MB.
- `Parcelable` richiede attenzione alla definizione del modello e al plugin Kotlin appropriato.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P2_DataPassing.kt -include-runtime -d P2_DataPassing.jar
2. Esegui il programma:
    java -jar P2_DataPassing.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Data passing — solution")
}
