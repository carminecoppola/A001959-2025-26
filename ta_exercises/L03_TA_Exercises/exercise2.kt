

// Exercise 2 - Safe Call Chain - Build a nested nullable chain and safely access deeply nested properties.

// Le 'data class' sono classi pensate per contenere dati.
// Kotlin genera automaticamente equals(), hashCode(), toString() e copy().
data class Address(val city: String?, val zip: String?)
data class User(val name: String, val address: Address?)

fun main() {
    val user: User? = User("Alice", Address("Rome", null))

    // L'operatore '?.' può essere concatenato per navigare strutture
    // annidate in modo sicuro, senza rischio di NPE.
    // Se uno qualsiasi dei passaggi intermedi è null, l'intera
    // espressione restituisce null (cortocircuito).
    val city = user?.address?.city ?: "Unknown"
    val zip  = user?.address?.zip  ?: "N/A"

    println("City: $city, ZIP: $zip")
    // Output: City: Rome, ZIP: N/A
    // (user non è null, address non è null, city = "Rome")
    // (user non è null, address non è null, zip = null → Elvis → "N/A")

    // 💡 Confronto con Java equivalente (senza Kotlin):
    //    String city = (user != null && user.getAddress() != null
    //                   && user.getAddress().getCity() != null)
    //                  ? user.getAddress().getCity() : "Unknown";
    //    In Kotlin è tutto in una riga leggibile!
}