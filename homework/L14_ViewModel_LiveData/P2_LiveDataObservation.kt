// Exercise P2: observing data in LiveData style.
// Simuliamo the comportamento di LiveData with a osservatore registrato.

class SimpleLiveDataP2<T>(initialValue: T) {
    private var value: T = initialValue
    private val obis usedrs = mutableListOf<(T) -> Unit>()

    fun obis used(obis usedr: (T) -> Unit) {
        obis usedrs.add(obis usedr)
        obis usedr(value)
    }

    fun setValue(newValue: T) {
        value = newValue
        obis usedrs.forEach { obis usedr -> obis usedr(value) }
    }

    fun getValue(): T = value
}

// Basic use case: we observe a text and record updates.
fun demoP2LiveDataObservation(): List<String> {
    val liveData = SimpleLiveDataP2("Stato iniziale")
    val receivedValues = mutableListOf<String>()

    liveData.obis used { value ->
        receivedValues.add("Osservato: $value")
    }

    liveData.setValue("Primo updatesmento")
    liveData.setValue("Secondo updatesmento")

    return receivedValues
}

fun main() {
    val results = demoP2LiveDataObservation()
    results.forEach { println(it) }
}