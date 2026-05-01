/*
Problem 4 - Resource Management

Obiettivo:
- Gestire correttamente risorse (camera, GPS, mediaPlayer, database) durante il lifecycle.
- Evitare memory leaks e consumi di batteria.

Spiegazione:
- Risorse critiche devono essere aperte in onResume e rilasciate in onPause/onStop
- Seguire il pattern:
  - onResume: acquisisci risorsa (camera, GPS, listener)
  - onPause: rilascia risorsa
  - Oppure: onStart (acquire), onStop (release)
- Tipi di risorse comuni:
  - Camera: risorsa esclusiva, alta priorità
  - GPS/Location: alto consumo batteria
  - MediaPlayer: rilascio in onStop
  - Database: chiudere cursor e connection in onDestroy o onPause
  - EventListener/BroadcastReceiver: unregister in onPause

Esempio di lifecycle per camera:
override fun onResume() {
    super.onResume()
    camera = Camera.open() // apri camera
}
override fun onPause() {
    camera?.release() // rilascia camera
    super.onPause()
}

Memory leak pattern SBAGLIATO:
- Aprire risorsa in onCreate e non rilasciarla mai
- Mantenere listener attivi in background

Edge cases:
- Activity può essere interrotta all'improvviso (crash, kill del processo)
- onDestroy non è sempre chiamato, quindi onPause è più affidabile
- Risorse non rilasciate causano battery drain e possibili crash di altre app
- Camera occupata da un'altra app cause exception se non gestita
- Database non chiuso causa file lock e memory leak
- Listener/observer non unregistrati causano memory leak

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P4_ResourceManagement.kt -include-runtime -d P4_ResourceManagement.jar
2. Esegui il programma:
    java -jar P4_ResourceManagement.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Resource management — solution")
}
