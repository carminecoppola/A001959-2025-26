/*
Problem 2 - Temperature Converter

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_TemperatureConverter.kt -include-runtime -d P2_TemperatureConverter.jar
2. Run the program:
   java -jar P2_TemperatureConverter.jar
*/

fun main() {
    print("Celsius: ")
    val c = readLine()!!.toDouble()
    val f = c * 9.0/5.0 + 32
    val k = c + 273.15
    println("Fahrenheit: ${String.format("%.2f", f)}")
    println("Kelvin:     ${String.format("%.2f", k)}")
}
