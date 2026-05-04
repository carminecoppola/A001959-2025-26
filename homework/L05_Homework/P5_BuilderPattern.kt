/*
Problem 5 - Builder Pattern

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P5_BuilderPattern.kt -include-runtime -d P5_BuilderPattern.jar
2. Run the program:
   java -jar P5_BuilderPattern.jar
*/

class QueryBuilder {
    private var table = ""
    private var condition: String? = null
    private var order: String? = null
    private var maxRows: Int? = null

    fun from(t: String) = apply { table = t }
    fun where(c: String) = apply { condition = c }
    fun orderBy(col: String) = apply { order = col }
    fun limit(n: Int) = apply { maxRows = n }

    fun build(): String {
        var q = "SELECT * FROM $table"
        condition?.let { q += " WHERE $it" }
        order?.let { q += " ORDER BY $it" }
        maxRows?.let { q += " LIMIT $it" }
        return q
    }
}

fun main() {
    val q = QueryBuilder()
        .from("users")
        .where("age > 18")
        .orderBy("name")
        .limit(10)
        .build()

    println(q)
}
