/*
Problem 5 - Navigation component

Obiettivo:
- Comprendere Navigation Component e Safe Args.
- Navigare in modo type-safe tra Fragment.

Spiegazione:
- Navigation Component usa un `nav_graph.xml` come mappa della navigazione.
- `NavHostFragment` ospita le destinazioni e gestisce le transizioni.
- `findNavController()` permette di eseguire la navigazione dal Fragment corrente.
- Safe Args genera classi type-safe per passare argomenti senza stringhe manuali.
- Questo riduce errori runtime e rende il flusso più leggibile.

Micro-esempio Android:
val action = HomeFragmentDirections.actionHomeToDetail(42)
findNavController().navigate(action)

Edge cases / common mistakes:
- Usare chiavi String manuali invece di Safe Args può introdurre errori runtime.
- Se il `NavHostFragment` non è configurato, la navigazione non funziona correttamente.
- Argomenti non coerenti tra source e destination causano crash o dati mancanti.
- Chiamare `findNavController()` fuori dal contesto corretto porta a errori.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P5_NavigationComponent.kt -include-runtime -d P5_NavigationComponent.jar
2. Esegui il programma:
    java -jar P5_NavigationComponent.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Navigation component — solution")
}
