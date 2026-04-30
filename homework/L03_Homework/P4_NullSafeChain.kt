/*
Problem 4 - Null-Safe Chain
Obiettivo:
- Creare un esempio con data class Order, Customer, Address e City.
- Usare le safe calls ?. per accedere a city.name.

Spiegazione codice:
- Ogni livello del modello è nullable tranne City.name, che è una String non nullable.
- La catena it?.customer?.address?.city?.name mostra come attraversare oggetti opzionali in sicurezza.
- Se un passaggio della catena è null, l'intera espressione restituisce null.
- L'Elvis operator ?: fornisce "Unknown city" come valore di fallback.

Edge cases:
- Customer null: la safe call interrompe la catena.
- Address null: la safe call interrompe la catena.
- City null: la safe call interrompe la catena.
- Order null: la safe call sull'elemento della lista restituisce null.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P4_NullSafeChain.kt -include-runtime -d P4_NullSafeChain.jar
2- Esegui il programma
    java -jar P4_NullSafeChain.jar
3- Questo esercizio non richiede input
*/

data class City(val name: String)
data class Address(val city: City?)
data class Customer(val address: Address?)
data class Order(val customer: Customer?)

fun main() {
    val o1 = Order(Customer(Address(City("Rome"))))
    val o2 = Order(Customer(null))
    val o3: Order? = null

    listOf(o1, o2, o3).forEach {
        println(it?.customer?.address?.city?.name ?: "Unknown city")
    }
}