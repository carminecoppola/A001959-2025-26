// Esercizio P3: TypeConverter per Room.
// Serve a trasformare un tipo complesso in un formato salvabile nel database.

data class AddressP3(
    val city: String,
    val street: String
)

object AddressTypeConverterP3 {
    fun fromAddress(address: AddressP3): String {
        return "${address.city}|${address.street}"
    }

    fun toAddress(value: String): AddressP3 {
        val parts = value.split("|")
        val city = parts.getOrElse(0) { "" }
        val street = parts.getOrElse(1) { "" }
        return AddressP3(city, street)
    }
}

// Caso d'uso di base: convertiamo un oggetto in stringa e poi lo ricostruiamo.
fun demoP3TypeConverters(): List<String> {
    val address = AddressP3("Roma", "Via Appia")
    val serialized = AddressTypeConverterP3.fromAddress(address)
    val restored = AddressTypeConverterP3.toAddress(serialized)

    return listOf(serialized, "${restored.city} - ${restored.street}")
}

fun main() {
    val results = demoP3TypeConverters()
    println("Serializzato: ${results[0]}")
    println("Ripristinato: ${results[1]}")
}