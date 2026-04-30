/*
Problem 3 - Inventory System
Obiettivo:
- Dato un inventario di Product, calcolare valore totale, prodotto più costoso e prodotti out-of-stock.

Spiegazione codice:
- Product è una data class con name, qty e price.
- sumOf calcola il valore totale dell'inventario (qty * price).
- maxByOrNull trova il prodotto con prezzo massimo.
- count individua quanti prodotti sono a zero di giacenza.

Edge cases:
- Lista vuota: sumOf ritorna 0, maxByOrNull ritorna null, count ritorna 0.
- Prodotti con prezzo negativo non sono esplicitamente esclusi.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P3_InventorySystem.kt -include-runtime -d P3_InventorySystem.jar
2- Esegui il programma
    java -jar P3_InventorySystem.jar
3- Questo esercizio non richiede input
*/

data class Product(val name: String, val qty: Int, val price: Double)

fun main() {
    val inv = listOf(
        Product("A", 10, 5.0),
        Product("B", 0, 20.0),
        Product("C", 5, 15.0)
    )

    println("Total: ${inv.sumOf { it.qty * it.price }}")
    println("Priciest: ${inv.maxByOrNull { it.price }?.name}")
    println("Out of stock: ${inv.count { it.qty == 0 }}")
}
