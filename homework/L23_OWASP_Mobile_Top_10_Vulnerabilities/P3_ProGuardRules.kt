// L23 - P3: regole ProGuard.
// Le regole aiutano a minimizzare and proteggere the code compilato.

class ProGuardRulesSimulatorP3 {
    fun rules(): List<String> {
        return listOf(
            "-keep class com.example.model.** { *; }",
            "-dontwarn okhttp3.**",
            "-keepattributes *Annotation*"
        )
    }

    fun obfuscate(className: String): String {
        return when (className) {
            "SecureDataManager" -> "SDM"
            "UserRepository" -> "UR"
            else -> className.take(3).uppercase()
        }
    }

    fun describe(): String {
        return "Regole ProGuard base for proteggere modelli and dipendenze notes"
    }
}

// Basic use case: we read a set minimo di regole.
fun demoL23P3ProGuardRules(): List<String> {
    val simulator = ProGuardRulesSimulatorP3()
    return listOf(
        simulator.describe(),
        "SecureDataManager -> ${simulator.obfuscate("SecureDataManager")}",
        "UserRepository -> ${simulator.obfuscate("UserRepository")}",
        simulator.rules().joinToString(separator = " | ")
    )
}

fun main() {
    println("=== ProGuard Rules ===")
    val results = demoL23P3ProGuardRules()
    results.forEach { println(it) }
}