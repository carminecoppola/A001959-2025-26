// Esercizio P2: osservazione di un dato in stile LiveData.
// Simuliamo il comportamento di LiveData con un osservatore registrato.

class SimpleLiveDataP2<T>(initialValue: T) {
    private var value: T = initialValue
    private val observers = mutableListOf<(T) -> Unit>()

    fun observe(observer: (T) -> Unit) {
        observers.add(observer)
        observer(value)
    }

    fun setValue(newValue: T) {
        value = newValue
        observers.forEach { observer -> observer(value) }
    }

    fun getValue(): T = value
}

// Caso d'uso di base: osserviamo un testo e registriamo gli aggiornamenti.
fun demoP2LiveDataObservation(): List<String> {
    val liveData = SimpleLiveDataP2("Stato iniziale")
    val receivedValues = mutableListOf<String>()

    liveData.observe { value ->
        receivedValues.add("Osservato: $value")
    }

    liveData.setValue("Primo aggiornamento")
    liveData.setValue("Secondo aggiornamento")

    return receivedValues
}