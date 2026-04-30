

// Exercise 1 - Basic Class - Create a BankAccount class with owner, balance, deposit, withdraw.

package l05.exercise1

// In Kotlin il COSTRUTTORE PRIMARIO si dichiara direttamente nell'intestazione
// della classe, dopo il nome. I parametri preceduti da 'val' o 'var' diventano
// automaticamente proprietà della classe (non serve dichiararle nel corpo).
class BankAccount(val owner: String, private var balance: Double = 0.0) {

    // 'require' è una funzione di precondizione: lancia un'eccezione
    // IllegalArgumentException se la condizione è falsa.
    // Il messaggio di errore è prodotto dalla lambda tra parentesi graffe.
    fun deposit(amount: Double) {
        require(amount > 0) { "Amount must be positive" }
        balance += amount
    }

    // La funzione restituisce Boolean per segnalare se il prelievo è andato a buon fine,
    // invece di lanciare un'eccezione — scelta di design più flessibile per il chiamante.
    fun withdraw(amount: Double): Boolean {
        if (amount > balance) return false  // fondi insufficienti
        balance -= amount
        return true
    }

    // Getter esplicito per 'balance' che è 'private var':
    // all'esterno della classe non si può né leggere né modificare direttamente
    fun getBalance() = balance

    // 'override' di toString() per una rappresentazione testuale leggibile.
    // Il simbolo '$' nella stringa è un carattere letterale (non una template),
    // perché precede "balance" senza spazio — ma qui viene usato come simbolo del dollaro.
    override fun toString() = "$owner: $$balance"
}

fun main() {
    val acc = BankAccount("Alice", 100.0)
    acc.deposit(50.0)                // balance diventa 150.0
    println(acc.withdraw(200.0))     // Output: false (200 > 150)
    println(acc)                     // Output: Alice: $150.0  (usa toString())
}