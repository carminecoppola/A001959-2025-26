/*
Problem 3 - Immutable Point

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_ImmutablePoint.kt -include-runtime -d P3_ImmutablePoint.jar
2. Run the program:
   java -jar P3_ImmutablePoint.jar
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
