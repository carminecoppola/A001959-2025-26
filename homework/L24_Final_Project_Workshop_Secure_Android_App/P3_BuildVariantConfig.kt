// L24 - P3: configurazione of the varianti di build.
// We show how to differentiate debug and release with simple settings.

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

// Basic use case: we compare debug and release.
fun demoL24P3BuildVariantConfig(): List<BuildVariantConfigP3> {
    val simulator = BuildVariantSimulatorP3()
    return listOf(simulator.debug(), simulator.release())
}

fun main() {
    println("=== Build Variant Config ===")
    val variants = demoL24P3BuildVariantConfig()
    variants.forEach { println(it) }
}