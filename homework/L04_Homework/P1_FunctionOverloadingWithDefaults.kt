/*
Problem 1 - Function Overloading with Defaults
Obiettivo:
- Scrivere una funzione greetUser(name, language = "en", formal = false).
- Supportare le lingue "en" e "it".
- Usare un saluto formale se formal è true, altrimenti informale.

Spiegazione codice:
- La funzione usa parametri di default per rendere opzionali language e formal.
- Il when seleziona la lingua da usare per costruire il messaggio.
- Un if interno distingue tra forma formale e informale.
- Il main mostra tre chiamate diverse per verificare i casi richiesti.

Edge cases:
- Lingua diversa da "en" o "it": il when cade nel ramo di default inglese.
- formal true e false producono messaggi diversi nella stessa lingua.
- I parametri di default permettono di chiamare la funzione con meno argomenti.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P1_FunctionOverloadingWithDefaults.kt -include-runtime -d P1_FunctionOverloadingWithDefaults.jar
2- Esegui il programma
    java -jar P1_FunctionOverloadingWithDefaults.jar
3- Questo esercizio non richiede input
*/

fun greetUser(name: String, language: String = "en", formal: Boolean = false): String {
    return when (language) {
        "it" -> if (formal) "Buongiorno, $name" else "Ciao, $name!"
        else -> if (formal) "Good morning, $name" else "Hey $name!"
    }
}

fun main() {
    println(greetUser("Alice"))
    println(greetUser("Bob", formal = true))
    println(greetUser("Carlo", "it", formal = true))
}