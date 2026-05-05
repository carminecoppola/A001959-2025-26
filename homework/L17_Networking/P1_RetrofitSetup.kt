// L17 - P1: setup di Retrofit.
// This example shows the struttura di base for parlare with a REST API.

data class RetrofitConfigP1(
    val baseUrl: String,
    val converterName: String
)

class RetrofitBuilderSimulatorP1 {
    fun build(baseUrl: String): RetrofitConfigP1 {
        return RetrofitConfigP1(
            baseUrl = baseUrl,
            converterName = "JsonConverter"
        )
    }
}

// Basic use case: we configure a client Retrofit simulato.
fun demoL17P1RetrofitSetup(): RetrofitConfigP1 {
    return RetrofitBuilderSimulatorP1().build("https://api.example.com/")
}

fun main() {
    println("=== Retrofit Setup ===")
    val config = demoL17P1RetrofitSetup()
    println("Base URL: ${config.baseUrl}")
    println("Converter: ${config.converterName}")
}