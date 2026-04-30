

// Exercise 3 - List Operations - Transform a list of products: filter in-stock, 
// sort by price, take top 3.

package l06.exercise3

// Data class: genera automaticamente equals, toString, hashCode
data class Product(
    val name: String,      // Nome prodotto
    val price: Double,     // Prezzo
    val inStock: Boolean 
)

fun main() {

    // Lista di prodotti (immutabile)
    val products = listOf(
        Product("A", 99.0, true),
        Product("B", 49.0, false),
        Product("C", 199.0, true),
        Product("D", 9.0, true)
    )

    val top3 = products

        // 1. Filtro: prendo solo i prodotti disponibili
        .filter { product ->
            product.inStock
        }

        // 2. Ordinamento: dal prezzo più basso al più alto
        .sortedBy { product ->
            product.price
        }

        // 3. Selezione: prendo solo i primi 3 elementi
        .take(3)

    // Stampa dei risultati
    top3.forEach { product ->
        println(product)
    }
}

