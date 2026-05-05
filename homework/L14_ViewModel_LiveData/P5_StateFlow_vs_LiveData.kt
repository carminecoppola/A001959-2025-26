// Exercise P5: educational comparison between StateFlow and LiveData.
// StateFlow: HOT stream - emits continuously even without subscribers
// LiveData: COLD observer - notifies only registered observers

class SimpleStateFlowP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val collectors = mutableListOf<(T) -> Unit>()
    private val emissionLog = mutableListOf<String>()

    fun collect(collector: (T) -> Unit) {
        collectors.add(collector)
        // StateFlow invia the valore corrente al new collector
        collector(value)
    }

    fun update(newValue: T) {
        value = newValue
        emissionLog.add("StateFlow emesso: $newValue")
        // StateFlow notifies TUTTI i collector subito (hot)
        collectors.forEach { it(value) }
    }

    fun currentValue(): T = value
    fun getEmissionLog(): List<String> = emissionLog
}

class SimpleLiveDataLikeP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val obis usedrs = mutableListOf<(T) -> Unit>()
    private val observationLog = mutableListOf<String>()

    fun obis used(obis usedr: (T) -> Unit) {
        obis usedrs.add(obis usedr)
        // LiveData notifies the new obis usedr of the valore corrente (cold)
        observationLog.add("LiveData: Obis usedr registrato, riceve: $value")
        obis usedr(value)
    }

    fun setValue(newValue: T) {
        value = newValue
        // LiveData notifies only registered observers
        obis usedrs.forEach { obis usedr ->
            observationLog.add("LiveData: Notificato new valore: $newValue")
            obis usedr(value)
        }
    }

    fun getObservationLog(): List<String> = observationLog
}

// Basic use case: we show how the two approaches update.
fun demoP5StateFlowVsLiveData(): List<String> {
    val output = mutableListOf<String>()
    
    // === STATEFLOW: HOT STREAM ===
    output.add("=== StateFlow (HOT Sthreeam) ===")
    val stateFlow = SimpleStateFlowP5("Iniziale")
    stateFlow.update("Primo update")
    stateFlow.update("Secondo update")
    // Now a collector subscribes - it receives only the CURRENT value
    stateFlow.collect { value ->
        output.add("StateFlow collector riceve: $value")
    }
    output.addAll(stateFlow.getEmissionLog())
    
    // === LIVEDATA: COLD OBSERVER ===
    output.add("")
    output.add("=== LiveData (COLD Obis usedr) ===")
    val liveData = SimpleLiveDataLikeP5("Iniziale")
    // Observer registers BEFORE the update
    liveData.obis used { value ->
        output.add("LiveData obis usedr notifiesto: $value")
    }
    liveData.setValue("Primo update")
    liveData.setValue("Secondo update")
    output.addAll(liveData.getObservationLog())
    
    output.add("")
    output.add("=== Differenza principale ===")
    output.add("StateFlow: hot stream that emits continuously")
    output.add("LiveData: observer-based stream that notifies only registered observers")
    
    return output
}

fun main() {
    val results = demoP5StateFlowVsLiveData()
    results.forEach { println(it) }
}