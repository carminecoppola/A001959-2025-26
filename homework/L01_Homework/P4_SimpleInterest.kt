/*
Problem 4 - Simple Interest
Obiettivo:
- Leggere principal, rate (%) e years.
- Calcolare l'interesse semplice:
  SI = (principal * rate * years) / 100
- Stampare interesse e totale finale.

Spiegazione codice:
- Si leggono tre valori numerici (Double).
- Si applica la formula dell'interesse semplice.
- Si stampa il valore dell'interesse e il totale (principal + interest) con 2 decimali.

Edge cases:
- Input non numerico genera errore in toDouble().
- Valori negativi sono accettati dal calcolo, perché non ci sono vincoli aggiuntivi.
 
Come eseguirlo da terminale:
1- Compila il file
  kotlinc P4_SimpleInterest.kt -include-runtime -d P4_SimpleInterest.jar
2- Esegui il programma
  java -jar P4_SimpleInterest.jar
3- Inserisci `Principal`, `Rate (%)` e `Years` quando richiesto a schermo.
*/

fun main() {
    print("Principal: "); val p = readLine()!!.toDouble()
    print("Rate (%): ");  val r = readLine()!!.toDouble()
    print("Years: ");     val t = readLine()!!.toDouble()
    val si = (p * r * t) / 100
    println("Simple Interest: ${String.format("%.2f", si)}")
    println("Total Amount:    ${String.format("%.2f", p + si)}")
}
