/*
Problem 3 - Function Composition
Obiettivo:
- Scrivere fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int.
- La funzione ritornata deve applicare prima g e poi f.

Spiegazione codice:
- compose restituisce una nuova funzione costruita con una lambda.
- La lambda interna riceve x, applica prima g(x) e poi f sul risultato.
- Questo è un esempio di composizione funzionale riutilizzabile.
- Il main mostra la composizione tra double e addOne.

Edge cases:
- Le funzioni passate a compose devono accettare e restituire Int.
- L'ordine è importante: prima g, poi f.
- Se le funzioni hanno effetti collaterali, anche la funzione composta li eredita.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P3_FunctionComposition.kt -include-runtime -d P3_FunctionComposition.jar
2- Esegui il programma
    java -jar P3_FunctionComposition.jar
3- Questo esercizio non richiede input
*/

fun compose(f: (Int)->Int, g: (Int)->Int): (Int)->Int = { x -> f(g(x)) }

fun main() {
    val double = { n: Int -> n * 2 }
    val addOne = { n: Int -> n + 1 }
    val doubleAddOne = compose(addOne, double)
    println(doubleAddOne(5))  // double(5)=10, then addOne(10)=11
}