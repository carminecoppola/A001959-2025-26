/*
Problem 3 - Immutable Point
Obiettivo:
- Creare una data class Point(x, y) con distanceTo, translate e scale.

Spiegazione codice:
- Point è immutabile (data class) e restituisce nuove istanze per translate e scale.
- distanceTo calcola la distanza euclidea usando sqrt dalla libreria math.

Edge cases:
- Le operazioni non modificano l'istanza originale ma ne ritornano una nuova.
- Valori non finiti (NaN/Infinity) seguono il comportamento numerico di Double.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P3_ImmutablePoint.kt -include-runtime -d P3_ImmutablePoint.jar
2- Esegui il programma
    java -jar P3_ImmutablePoint.jar
3- Questo esercizio non richiede input
*/

import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point) =
        sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y))

    fun translate(dx: Double, dy: Double) = Point(x + dx, y + dy)

    fun scale(factor: Double) = Point(x * factor, y * factor)
}

fun main() {
    val p = Point(3.0, 4.0)

    println(p.distanceTo(Point(0.0, 0.0)))  // 5.0
    println(p.translate(1.0, 1.0))
    println(p.scale(2.0))
}
