// Exercise P1: definition of an Entity for a Room database.
// Here we simulate a table with a simple Kotlin data class.

data class BookEntityP1(
    val id: Int,
    val title: String,
    val author: String
)

// Basic use case: we create a list of entities as if they were database records.
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