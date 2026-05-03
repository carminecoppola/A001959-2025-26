// L17 - P4: interceptor OkHttp.
// Un interceptor osserva la richiesta e puo aggiungere log, header, token, autenticazione.

data class RequestLogP4(
    val method: String,
    val url: String,
    val headers: Map<String, String> = emptyMap()
)

class OkHttpInterceptorSimulatorP4 {
    private val authToken = "Bearer-token-12345"

    fun intercept(method: String, url: String): RequestLogP4 {
        // Qui aggiungiamo header/token automaticamente a tutte le richieste
        val headers = mutableMapOf(
            "Authorization" to authToken,
            "Content-Type" to "application/json",
            "User-Agent" to "MyApp/1.0"
        )

        // Log della richiesta
        println("Interceptor: Aggiunta autenticazione per $method $url")

        return RequestLogP4(method = method, url = url, headers = headers)
    }
}

// Caso d'uso di base: logghiamo una richiesta GET con headers aggiunti.
fun demoL17P4OkHttpInterceptor(): RequestLogP4 {
    return OkHttpInterceptorSimulatorP4().intercept("GET", "https://api.example.com/users")
}

fun main() {
    println("=== OkHttp Interceptor ===")
    val log = demoL17P4OkHttpInterceptor()
    println("Metodo: ${log.method}")
    println("URL: ${log.url}")
    println("Headers aggiunti:")
    log.headers.forEach { (key, value) -> println("  $key: $value") }
}