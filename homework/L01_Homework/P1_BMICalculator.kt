/*
Problem 1 - BMI Calculator
Obiettivo:
- Leggere peso (kg) e altezza (m)
- Calcolare il BMI con la formula: BMI = weight / (height * height)
- Stampare la categoria: Underweight / Normal / Overweight / Obese

Spiegazione codice:
- Si leggono due valori numerici da input standard.
- Si calcola il BMI.
- Un blocco when assegna la categoria in base alle soglie richieste.
- Si stampa il BMI con 2 decimali e la categoria.

Edge cases:
- Se l'utente inserisce testo non numerico, toDouble() genera errore.
- Se l'altezza è 0, si ha una divisione per zero.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P1_BMICalculator.kt -include-runtime -d P1_BMICalculator.jar
2- Esegui il programma
    java -jar P1_BMICalculator.jar
3- Inserisci i valori richiesti (peso e altezza) quando richiesto a schermo.
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
