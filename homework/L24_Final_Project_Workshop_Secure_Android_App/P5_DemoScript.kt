// L24 - P5: script di demo.
// Questo file descrive i passi di una dimostrazione dell'app sicura.

class DemoScriptSimulatorP5 {
    fun script(): List<String> {
        return listOf(
            "1. Avviare l'app",
            "2. Autenticazione biometrica con fallback PIN",
            "3. Mostrare il token salvato in modo sicuro",
            "4. Aprire la dashboard protetta",
            "5. Verificare il salvataggio cifrato",
            "6. Effettuare logout e invalidare la sessione"
        )
    }

    fun run(): String {
        return script().joinToString(separator = "\n")
    }
}

// Caso d'uso di base: generiamo lo script testuale della demo.
fun demoL24P5DemoScript(): String {
    return DemoScriptSimulatorP5().run()
}

fun main() {
    println("=== Demo Script ===")
    println(demoL24P5DemoScript())
}