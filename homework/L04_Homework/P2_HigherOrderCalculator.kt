/*
Problem 2 - Higher-Order Calculator
Obiettivo:
- Scrivere fun calculate(a: Double, b: Double, op: (Double, Double) -> Double): Double.
- Creare lambda per somma, sottrazione, moltiplicazione e divisione.

Spiegazione codice:
- La funzione calculate riceve una higher-order function come parametro.
- La lambda op definisce l'operazione da eseguire tra due numeri.
- Questo rende la logica riutilizzabile senza duplicare codice per ogni operazione.
- Il main mostra l'uso di lambda per addizione e moltiplicazione.

Edge cases:
- La funzione non valida il divisore perché la logica richiesta lascia il controllo alla lambda.
- I numeri sono Double, quindi supportano valori decimali.
- Le lambda possono essere sostituite facilmente con altre operazioni compatibili.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P2_HigherOrderCalculator.kt -include-runtime -d P2_HigherOrderCalculator.jar
2- Esegui il programma
    java -jar P2_HigherOrderCalculator.jar
3- Questo esercizio non richiede input
*/

fun calculate(a: Double, b: Double, op: (Double, Double) -> Double) = op(a, b)

fun main() {
    val add = { x: Double, y: Double -> x + y }
    val sub = { x: Double, y: Double -> x - y }
    val mul = { x: Double, y: Double -> x * y }
    val div = { x: Double, y: Double -> if (y != 0.0) x / y else Double.NaN }

    println(calculate(10.0, 3.0, add))
    println(calculate(10.0, 3.0, mul))
    println(calculate(10.0, 3.0, sub))
    println(calculate(10.0, 3.0, div))


}