// L17 - P4: interceptor OkHttp.
// Un interceptor osserva la richiesta e puo aggiungere log o modificare i dati.

data class RequestLogP4(
    val method: String,
    val url: String
)

class OkHttpInterceptorSimulatorP4 {
    fun intercept(method: String, url: String): RequestLogP4 {
        // Qui stiamo registrando le informazioni principali della richiesta.
        return RequestLogP4(method = method, url = url)
    }
}

// Caso d'uso di base: logghiamo una richiesta GET.
fun demoL17P4OkHttpInterceptor(): RequestLogP4 {
    return OkHttpInterceptorSimulatorP4().intercept("GET", "https://api.example.com/users")
}