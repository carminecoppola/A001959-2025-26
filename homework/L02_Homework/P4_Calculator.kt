/*
Problem 4 - Calculator
Obiettivo:
- Leggere due numeri e un operatore tra +, -, * e /.
- Stampare il risultato.
- Gestire la divisione per zero come caso di errore.

Spiegazione codice:
- La main legge il primo numero come Double per supportare anche valori decimali.
- Poi legge l'operatore e il secondo numero.
- La when seleziona l'operazione corretta in base al simbolo inserito.
- Per la divisione, viene controllato che il divisore sia diverso da zero.
- Se l'operatore non è valido o la divisione non è consentita, il risultato diventa null e viene stampato "Error".

Edge cases:
- Divisione per zero: restituisce null invece di generare un crash logico.
- Operatore non supportato: viene stampato "Error".
- Input non numerico: readLine()!!.toDouble() può fallire, ma la logica rimane quella richiesta.
- Numeri negativi e decimali sono supportati perché si usa Double.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P4_Calculator.kt -include-runtime -d P4_Calculator.jar
2- Esegui il programma
    java -jar P4_Calculator.jar
3- Inserisci i valori richiesti quando richiesto a schermo
*/

fun main() {
    print("Num1: "); val a = readLine()!!.toDouble()
    print("Op (+,-,*,/): "); val op = readLine()!!
    print("Num2: "); val b = readLine()!!.toDouble()
    val result = when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else null
        else -> null
    }
    println(if (result != null) "= $result" else "Error")
}
