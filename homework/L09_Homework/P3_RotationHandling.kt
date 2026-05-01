/*
Problem 3 - Rotation Handling

Obiettivo:
- Comprendere cosa accade all'Activity durante la rotazione dello schermo.
- Gestire correttamente lo stato durante i configuration changes.

Spiegazione:
- Rotazione dello schermo causa la distruzione e ricreazione dell'Activity
- Lifecycle completo durante rotazione: onPause → onStop → onDestroy → onCreate → onStart → onResume
- Tutti i dati in memoria vengono persi a meno di salvataggio
- ViewModel sopravvive alla rotazione perché non è legato all'Activity
- ConfigurationChange può essere gestito in AndroidManifest (android:configChanges)

Comportamento standard (senza android:configChanges):
1. Utente ruota dispositivo
2. Sistema rileva orientation change
3. Activity viene distrutta (onDestroy)
4. Activity viene ricreata con nuova orientazione (onCreate)
5. Il Bundle con stato salvato viene passato a onCreate

Soluzioni per mantenere stato:
- onSaveInstanceState + Bundle: per dati primitivi
- ViewModel: per dati complessi (PREFERITO)
- SharedPreferences: per persistenza a lungo termine

Edge cases:
- Dati non salvati vengono persi durante rotazione
- Doppia instanziazione di risorse se non gestito correttamente
- Process death durante rotazione: Bundle potrebbe non essere disponibile
- ViewModel viene ricreato solo se l'Activity non viene distrutta completamente
- Rotazione veloce multipla può causare comportamenti inattesi

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P3_RotationHandling.kt -include-runtime -d P3_RotationHandling.jar
2. Esegui il programma:
    java -jar P3_RotationHandling.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("Rotation handling — solution")
}
