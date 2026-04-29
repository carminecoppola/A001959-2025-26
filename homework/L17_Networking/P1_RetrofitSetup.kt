// L17 - P1: setup di Retrofit.
// Questo esempio mostra la struttura di base per parlare con una REST API.

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

// Caso d'uso di base: configuriamo un client Retrofit simulato.
fun demoL17P1RetrofitSetup(): RetrofitConfigP1 {
    return RetrofitBuilderSimulatorP1().build("https://api.example.com/")
}