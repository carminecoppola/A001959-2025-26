// Esercizio P5: confronto didattico tra StateFlow e LiveData.
// Qui non usiamo le librerie Android reali, ma mostriamo l'idea di base.

class SimpleStateFlowP5<T>(initialValue: T) {
    private var value: T = initialValue

    fun update(newValue: T) {
        value = newValue
    }

    fun currentValue(): T = value
}

class SimpleLiveDataLikeP5<T>(initialValue: T) {
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
}

// Caso d'uso di base: mostriamo come si aggiornano i due approcci.
fun demoP5StateFlowVsLiveData(): List<String> {
    val stateFlow = SimpleStateFlowP5("Iniziale")
    val liveData = SimpleLiveDataLikeP5("Iniziale")
    val output = mutableListOf<String>()

    liveData.observe { value ->
        output.add("LiveData: $value")
    }

    stateFlow.update("Aggiornato con StateFlow")
    liveData.setValue("Aggiornato con LiveData")

    output.add("StateFlow: ${stateFlow.currentValue()}")
    return output
}