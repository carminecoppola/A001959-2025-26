// L24 - P3: configurazione delle varianti di build.
// Mostriamo come differenziare debug e release con impostazioni semplici.

data class BuildVariantConfigP3(
    val name: String,
    val loggingEnabled: Boolean,
    val apiBaseUrl: String
)

class BuildVariantSimulatorP3 {
    fun debug(): BuildVariantConfigP3 {
        return BuildVariantConfigP3(
            name = "debug",
            loggingEnabled = true,
            apiBaseUrl = "https://dev.api.example.com/"
        )
    }

    fun release(): BuildVariantConfigP3 {
        return BuildVariantConfigP3(
            name = "release",
            loggingEnabled = false,
            apiBaseUrl = "https://api.example.com/"
        )
    }
}

// Caso d'uso di base: confrontiamo debug e release.
fun demoL24P3BuildVariantConfig(): List<BuildVariantConfigP3> {
    val simulator = BuildVariantSimulatorP3()
    return listOf(simulator.debug(), simulator.release())
}