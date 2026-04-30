

// Exercise 2 - Data Class - Create a Product data class and show equals, copy, toString, destructuring.

package l05.exercise2

// Una 'data class' è una classe speciale pensata per contenere dati.
// Il compilatore genera automaticamente:
//  - equals() e hashCode() → confronto strutturale (per valore, non per riferimento)
//  - toString()            → rappresentazione leggibile
//  - copy()                → copia con possibilità di modificare alcuni campi
//  - componentN()          → funzioni per il destructuring
data class Product(val id: Int, val name: String, val price: Double)

fun main() {
    val p1 = Product(1, "Laptop", 999.99)
    val p2 = Product(1, "Laptop", 999.99)

    // Con una classe normale, == confronterebbe i RIFERIMENTI (come in Java con ==).
    // Con una data class, == confronta i VALORI dei campi (strutturale).
    println(p1 == p2)   // Output: true  (stessi valori, anche se oggetti diversi)

    // toString() generato automaticamente in formato leggibile
    println(p1)         // Output: Product(id=1, name=Laptop, price=999.99)

    // copy() crea un nuovo oggetto identico, tranne per i campi specificati.
    // L'originale p1 rimane invariato (le data class sono tipicamente immutabili).
    val sale = p1.copy(price = 799.99)
    println(sale)       // Output: Product(id=1, name=Laptop, price=799.99)

    // DESTRUCTURING: le data class generano funzioni component1(), component2()...
    // che permettono di "spacchettare" l'oggetto in variabili separate
    val (id, name, price) = p1
    println("Product $id: $name costs $$price")  // Output: Laptop costs $999.99
}