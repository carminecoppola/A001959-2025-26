/*
Problem 2 - Manifest Components

Obiettivo:
- Comprendere il ruolo del file AndroidManifest.xml.

Spiegazione:
- AndroidManifest.xml dichiara informazioni fondamentali dell’app:
  - Activity
  - Permissions
  - Services
  - BroadcastReceivers
  - Metadata dell’app

Esempio AndroidManifest.xml:

<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

Questo esempio indica che MainActivity è il punto di ingresso dell’app.

Edge cases:
- Non ci sono input o logica dinamica.
- In un progetto reale, errori nel manifest possono impedire l’avvio dell’app.

Come eseguirlo:
kotlinc P2_ManifestComponents.kt -include-runtime -d P2_ManifestComponents.jar && java -jar P2_ManifestComponents.jar
*/

fun main() {
    println("Manifest components — solution")
}