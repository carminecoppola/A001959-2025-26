// Esercizio P1: definizione di una Entity per un database Room.
// Qui simuliamo una tabella con una semplice data class Kotlin.

data class BookEntityP1(
    val id: Int,
    val title: String,
    val author: String
)

// Caso d'uso di base: creiamo una lista di entity come se fossero record del database.
fun demoP1EntityDefinition(): List<BookEntityP1> {
    return listOf(
        BookEntityP1(1, "Kotlin Basics", "Marco"),
        BookEntityP1(2, "Android Guide", "Sara")
    )
}

fun main() {
    val books = demoP1EntityDefinition()
    books.forEach { println("ID: ${it.id}, Title: ${it.title}, Author: ${it.author}") }
}