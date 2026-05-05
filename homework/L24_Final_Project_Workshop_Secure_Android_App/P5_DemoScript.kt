// L24 - P5: script di demo.
// This file describes the steps of a secure app demo.

class DemoScriptSimulatorP5 {
    fun script(): List<String> {
        return listOf(
            "1. Launch the app",
            "2. Biometric authentication with PIN fallback",
            "3. Show the securely stored token",
            "4. Open the protected dashboard",
            "5. Verify encrypted storage",
            "6. Log out and invalidate the session"
        )
    }

    fun run(): String {
        return script().joinToString(separator = "\n")
    }
}

// Basic use case: we generate the textual demo script.
fun demoL24P5DemoScript(): String {
    return DemoScriptSimulatorP5().run()
}

fun main() {
    println("=== Demo Script ===")
    println(demoL24P5DemoScript())
}