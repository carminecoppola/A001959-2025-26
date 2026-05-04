/*
Problem 1 - BMI Calculator

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_BMICalculator.kt -include-runtime -d P1_BMICalculator.jar
2. Run the program:
   java -jar P1_BMICalculator.jar
*/

fun main() {
    print("Weight (kg): ")
    val weight = readLine()!!.toDouble()
    print("Height (m): ")
    val height = readLine()!!.toDouble()

    val bmi = weight / (height * height)
    val category = when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal"
        bmi < 30.0 -> "Overweight"
        else       -> "Obese"
    }
    println("BMI: ${String.format("%.2f", bmi)} — $category")
}
