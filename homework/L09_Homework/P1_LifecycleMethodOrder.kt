/*
Problem 1 - Lifecycle Method Order

Obiettivo:
- Comprendere l'ordine esatto dei metodi del ciclo di vita di un'Activity.
- Identificare quando ogni metodo viene chiamato durante l'esecuzione.

Spiegazione:
- Il lifecycle di un'Activity segue questo ordine:
  - onCreate: chiamato quando l'Activity viene creata per la prima volta
  - onStart: Activity diventa visibile
  - onResume: Activity ottiene il focus e diventa interattiva
  - onPause: Activity perde il focus (ma rimane visibile, es. dialog in primo piano)
  - onStop: Activity non è più visibile
  - onDestroy: Activity viene distrutta
- Ogni transizione ha un motivo specifico:
  - onCreate → onStart → onResume: avvio normale
  - onResume → onPause → onStop: l'app va in background
  - onStop → onDestroy: Activity distrutta dal sistema o per rotazione

Esempio di call stack:
1. Creazione app: onCreate → onStart → onResume
2. Utente preme home: onPause → onStop
3. Ritorno app: onStart → onResume
4. Chiusura app: onPause → onStop → onDestroy

Edge cases:
- Activity può essere distrutta dal sistema per liberare memoria, non solo da back button
- Rotazione dello schermo riavvia il lifecycle (onCreate → onDestroy)
- onDestroy non è sempre garantito (crash o kill del processo)
- Metodi intermedi possono essere saltati in scenari di emergenza

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P1_LifecycleMethodOrder.kt -include-runtime -d P1_LifecycleMethodOrder.jar
2. Esegui il programma:
    java -jar P1_LifecycleMethodOrder.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Lifecycle method order — solution")
}
