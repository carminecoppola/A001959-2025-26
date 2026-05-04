/*
Problem 3 - Inventory System

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_InventorySystem.kt -include-runtime -d P3_InventorySystem.jar
2. Run the program:
   java -jar P3_InventorySystem.jar
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
