/*
Problem 4 - Intent security

Obiettivo:
- Comprendere i rischi di sicurezza legati agli Intent.
- Imparare a validare dati ricevuti da sorgenti esterne.

Spiegazione:
- Gli Intent provenienti dall'esterno non devono mai essere considerati affidabili.
- Bisogna validare extras, action e data prima di usarli.
- Per Activity interne conviene usare `android:exported="false"` quando possibile.
- L'obiettivo è evitare intent injection, accessi non previsti e comportamenti non sicuri.

Micro-esempio Android:
<activity
    android:name=".InternalActivity"
    android:exported="false" />

val safeId = intent.getIntExtra("EXTRA_ID", -1)
if (safeId == -1) {
    finish()
}

Edge cases / common mistakes:
- Chiavi extras sbagliate o mancanti possono produrre valori default inattesi.
- Activity esportate senza validazione aumentano il rischio di abuso.
- Dati non sanificati possono causare intent injection o logica errata.
- Non fidarsi mai di Intent esterni come se fossero input interni.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P4_IntentSecurity.kt -include-runtime -d P4_IntentSecurity.jar
2. Esegui il programma:
    java -jar P4_IntentSecurity.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Intent security — solution")
}
