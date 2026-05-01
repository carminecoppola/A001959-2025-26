/*
Problem 4 - ViewBinding setup

Obiettivo:
- Comprendere come configurare e usare ViewBinding.
- Evitare l'uso di `findViewById` quando non necessario.

Spiegazione:
- ViewBinding genera una classe type-safe per ogni XML layout.
- Permette accesso diretto alle View senza cast manuali.
- Va abilitato nel file Gradle con `buildFeatures { viewBinding = true }`.
- Una volta generato il binding, si può leggere e scrivere sulle View in modo sicuro.
- È una soluzione più robusta di `findViewById` perché riduce errori a runtime.

Micro-esempio Gradle:
android {
    buildFeatures {
        viewBinding = true
    }
}

Micro-esempio Kotlin:
binding.btnLogin.setOnClickListener {
    binding.tvMessage.text = "Hello, Android!"
}

Edge cases / common mistakes:
- Se ViewBinding non è abilitato, la classe binding non viene generata.
- Se il nome del layout cambia, cambia anche il nome della classe binding.
- Usare il binding fuori dal ciclo di vita corretto può creare riferimenti non validi.
- Dimenticare di inizializzarlo correttamente porta a errori di compilazione o nullability.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P4_ViewBindingSetup.kt -include-runtime -d P4_ViewBindingSetup.jar
2. Esegui il programma:
    java -jar P4_ViewBindingSetup.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("ViewBinding setup — solution")
}
