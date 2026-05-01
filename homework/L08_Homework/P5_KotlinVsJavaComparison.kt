/*
Problem 5 - Kotlin vs Java Comparison

Obiettivo:
- Comprendere perché Kotlin è preferito nello sviluppo Android moderno.

Spiegazione:
- Kotlin è più conciso di Java.
- Offre null safety integrata.
- Supporta lambda, funzioni di ordine superiore e coroutines.
- È interoperabile con Java.
- Riduce boilerplate rispetto a Java.

Esempio Kotlin:

val name: String? = null
println(name ?: "N/A")

Esempio concettuale:
- In Kotlin il tipo nullable deve essere dichiarato esplicitamente con ?
- Questo riduce il rischio di NullPointerException rispetto a codice Java tradizionale.

Edge cases:
- Non ci sono input.
- L’esercizio è teorico e serve a confrontare concetti, non a implementare logica complessa.

Come eseguirlo:
kotlinc P5_KotlinVsJavaComparison.kt -include-runtime -d P5_KotlinVsJavaComparison.jar && java -jar P5_KotlinVsJavaComparison.jar
*/

fun main() {
    println("Kotlin vs Java comparison — solution")
}