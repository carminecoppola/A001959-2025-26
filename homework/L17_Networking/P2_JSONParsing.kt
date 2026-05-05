// L17 - P2: parsing JSON with Retrofit.
// Here we simulate the transition from a JSON string to a Kotlin object.

data class ArticleP2(
    val id: Int,
    val title: String
)

class JsonParserSimulatorP2 {
    // Parsing molto semplice for mantenere l'esempio didattico.
    fun parseArticle(json: String): ArticleP2 {
        val id = Regex("\"id\"\\s*:\\s*(\\d+)").find(json)?.groupValues?.get(1)?.toInt() ?: 0
        val title = Regex("\"title\"\\s*:\\s*\"([^\"]+)\"").find(json)?.groupValues?.get(1) ?: ""
        return ArticleP2(id = id, title = title)
    }
}

// Basic use case: we transform a JSON response into a Kotlin model.
fun demoL17P2JSONParsing(): ArticleP2 {
    val json = """{"id": 7, "title": "Introduzione a Retrofit"}"""
    return JsonParserSimulatorP2().parseArticle(json)
}

fun main() {
    println("=== JSON Parsing ===")
    val article = demoL17P2JSONParsing()
    println("ID: ${article.id}")
    println("Title: ${article.title}")
}
