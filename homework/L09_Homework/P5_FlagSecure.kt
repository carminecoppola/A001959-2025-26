/*
Problem 5 - FLAG_SECURE Usage

Obiettivo:
- Comprendere l'uso di FLAG_SECURE per proteggere il contenuto sensibile.
- Impedire screenshot e registrazioni dello schermo.

Spiegazione:
- FLAG_SECURE è un flag della Window che impedisce il rendering su display non sicuri
- Principale utilizzo: bloccare screenshot e screen recording
- Utile per app sensibili: banche, wallet, password manager, dati personali
- Sintassi in Kotlin:
  window.setFlags(
      WindowManager.LayoutParams.FLAG_SECURE,
      WindowManager.LayoutParams.FLAG_SECURE
  )
- Deve essere impostato in onCreate prima di setContentView()
- Effetti:
  - screenshot non cattura il contenuto (schermata nera)
  - screen recording mostra schermata nera
  - app switcher potrebbe non mostrare preview

Quando usare FLAG_SECURE:
- Login screen
- Dettagli carta di credito
- OTP/codici di autenticazione
- Dati personali sensibili (SSN, documento d'identità)

Quando NON usare:
- Contenuto pubblico (news, foto, articoli)
- Causa piccoli overhead di performance
- Potrebbe interferire con accessibility

Edge cases:
- FLAG_SECURE non protegge da attacchi a livello di debugger
- Non impedisce capture via USB debugging o framebuffer
- Comportamento varia tra Android versions e OEM
- Disabilitare via developer mode potrebbe essere possibile
- Non è una soluzione di sicurezza completa (parte di defense-in-depth)
- Incompatibilità con alcuni screen recorder enterprise

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P5_FlagSecure.kt -include-runtime -d P5_FlagSecure.jar
2. Esegui il programma:
    java -jar P5_FlagSecure.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("FLAG_SECURE usage — solution")
}
