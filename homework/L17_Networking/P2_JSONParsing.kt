// L17 - P2: parsing JSON con Retrofit.
// Qui similiamo il passaggio da una stringa JSON a un oggetto Kotlin.

data class ArticleP2(
    val id: Int,
    val title: String
)

class JsonParserSimulatorP2 {
    // Parsing molto semplice per mantenere l'esempio didattico.
    fun parseArticle(json: String): ArticleP2 {
        val id = Regex("\\"id\\"\\s*:\\s*(\\d+)").find(json)?.groupValues?.get(1)?.toInt() ?: 0
        val title = Regex("\\"title\\"\\s*:\\s*\\"([^\\"]+)\\"").find(json)?.groupValues?.get(1) ?: ""
        return ArticleP2(id = id, title = title)
    }
}

// Caso d'uso di base: trasformiamo una risposta JSON in un modello Kotlin.
fun demoL17P2JSONParsing(): ArticleP2 {
    val json = """{"id": 7, "title": "Introduzione a Retrofit"}"""
    return JsonParserSimulatorP2().parseArticle(json)
}