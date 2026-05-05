// L17 - P4: interceptor OkHttp.
// An interceptor inspects requests and can add logs, headers, tokens, and authentication.

data class RequestLogP4(
    val method: String,
    val url: String,
    val headers: Map<String, String> = emptyMap()
)

class OkHttpInterceptorSimulatorP4 {
    private val authToken = "Bearer-token-12345"

    fun intercept(method: String, url: String): RequestLogP4 {
        // Here we automatically add headers/tokens to all requests
        val headers = mutableMapOf(
            "Authorization" to authToken,
            "Content-Type" to "application/json",
            "User-Agent" to "MyApp/1.0"
        )

        // Request log
        println("Interceptor: Authentication added for $method $url")

        return RequestLogP4(method = method, url = url, headers = headers)
    }
}

// Basic use case: we log a GET request with added headers.
fun demoL17P4OkHttpInterceptor(): RequestLogP4 {
    return OkHttpInterceptorSimulatorP4().intercept("GET", "https://api.example.com/users")
}

fun main() {
    println("=== OkHttp Interceptor ===")
    val log = demoL17P4OkHttpInterceptor()
    println("Metodo: ${log.method}")
    println("URL: ${log.url}")
    println("Added headers:")
    log.headers.forEach { (key, value) -> println("  $key: $value") }
}