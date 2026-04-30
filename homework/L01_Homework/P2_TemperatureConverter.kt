/*
Problem 2 - Temperature Converter
Obiettivo:
- Leggere una temperatura in Celsius.
- Convertirla in Fahrenheit e Kelvin.

Formule:
- F = C * 9/5 + 32
- K = C + 273.15

Spiegazione codice:
- Si legge il valore Celsius come Double.
- Si applicano direttamente le due formule richieste.
- Si stampano i risultati con 2 decimali.

Edge cases:
- Input non numerico causa errore con toDouble().
- Sono accettate temperature negative (valide per Celsius/Fahrenheit).

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P2_TemperatureConverter.kt -include-runtime -d P2_TemperatureConverter.jar
2- Esegui il programma
    java -jar P2_TemperatureConverter.jar
3- Inserisci il valore in Celsius quando richiesto a schermo.
*/

fun main() {
    print("Celsius: ")
    val c = readLine()!!.toDouble()
    val f = c * 9.0/5.0 + 32
    val k = c + 273.15
    println("Fahrenheit: ${String.format("%.2f", f)}")
    println("Kelvin:     ${String.format("%.2f", k)}")
}
