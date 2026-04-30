/*
Problem 5 - Builder Pattern
Obiettivo:
- Creare QueryBuilder per costruire query SQL-like con from, where, orderBy, limit e build.

Spiegazione codice:
- QueryBuilder mantiene campi interni modificabili e restituisce se stesso con apply()
- I metodi from/where/orderBy/limit ritornano QueryBuilder per concatenare le chiamate.
- build compone la stringa SQL considerando solo le parti presenti.

Edge cases:
- Table vuota produrrà una query con FROM seguito da stringa vuota.
- Campi opzionali (where/order/limit) vengono ignorati se null.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P5_BuilderPattern.kt -include-runtime -d P5_BuilderPattern.jar
2- Esegui il programma
    java -jar P5_BuilderPattern.jar
3- Questo esercizio non richiede input
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
