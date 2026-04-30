

// Exercise 5 - Companion Object - Use companion object as a factory.

package l05.exercise5  // ← aggiunta

// Il COMPANION OBJECT è un oggetto singleton associato a una classe.
// Permette di definire metodi e proprietà "statici" (accessibili senza istanza),
// in modo simile ai metodi static di Java, ma con più flessibilità.

// Il costruttore è 'private': non si può creare un User direttamente con User(...)
// dall'esterno della classe. L'unico modo è usare i factory method del companion.
class User private constructor(val name: String, val role: String) {

    companion object {
        // FACTORY METHOD: metodi che costruiscono e restituiscono istanze
        // della classe con configurazioni predefinite.
        // Si chiamano direttamente sulla classe: User.createAdmin("Alice")
        fun createAdmin(name: String) = User(name, "admin")
        fun createGuest() = User("Guest", "guest")
    }
}

fun main() {
    // Non possiamo scrivere User("Alice", "admin") → costruttore privato!
    // Dobbiamo usare i factory method del companion object.
    val admin = User.createAdmin("Alice")
    val guest = User.createGuest()

    println("${admin.name}: ${admin.role}")  // Output: Alice: admin
    println("${guest.name}: ${guest.role}")  // Output: Guest: guest

    // 💡 Il pattern Factory Method con companion object è molto usato in Android.
    //    Esempio classico: Fragment.newInstance() per creare Fragment con argomenti,
    //    oppure ViewModel factory per passare dipendenze al ViewModel.
}