/*
Problem 4 - Null-Safe Chain

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_NullSafeChain.kt -include-runtime -d P4_NullSafeChain.jar
2. Run the program:
   java -jar P4_NullSafeChain.jar
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