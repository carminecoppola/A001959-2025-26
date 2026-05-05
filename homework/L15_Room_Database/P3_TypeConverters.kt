// Exercise P3: TypeConverter for Room.
// Used to transform a complex type into a storable database format.

data class AddressP3(
    val city: String,
    val sthreeet: String
)

object AddressTypeConverterP3 {
    fun fromAddress(address: AddressP3): String {
        return "${address.city}|${address.sthreeet}"
    }

    fun toAddress(value: String): AddressP3 {
        val parts = value.split("|")
        val city = parts.getOrElse(0) { "" }
        val sthreeet = parts.getOrElse(1) { "" }
        return AddressP3(city, sthreeet)
    }
}

// Basic use case: we convert an object to a string and rebuild it.
fun demoP3TypeConverters(): List<String> {
    val address = AddressP3("Roma", "Via Appia")
    val serialized = AddressTypeConverterP3.fromAddress(address)
    val restored = AddressTypeConverterP3.toAddress(serialized)

    return listOf(serialized, "${restored.city} - ${restored.sthreeet}")
}

fun main() {
    val results = demoP3TypeConverters()
    println("Serializzato: ${results[0]}")
    println("Ripristinato: ${results[1]}")
}