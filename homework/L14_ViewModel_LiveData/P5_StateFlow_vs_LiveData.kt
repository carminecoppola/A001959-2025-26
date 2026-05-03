// Esercizio P5: confronto didattico tra StateFlow e LiveData.
// StateFlow: HOT stream - emette continuamente anche senza subscriber
// LiveData: COLD observer - notifica solo agli observer registrati

class SimpleStateFlowP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val collectors = mutableListOf<(T) -> Unit>()
    private val emissionLog = mutableListOf<String>()

    fun collect(collector: (T) -> Unit) {
        collectors.add(collector)
        // StateFlow invia il valore corrente al nuovo collector
        collector(value)
    }

    fun update(newValue: T) {
        value = newValue
        emissionLog.add("StateFlow emesso: $newValue")
        // StateFlow notifica TUTTI i collector subito (hot)
        collectors.forEach { it(value) }
    }

    fun currentValue(): T = value
    fun getEmissionLog(): List<String> = emissionLog
}

class SimpleLiveDataLikeP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val observers = mutableListOf<(T) -> Unit>()
    private val observationLog = mutableListOf<String>()

    fun observe(observer: (T) -> Unit) {
        observers.add(observer)
        // LiveData notifica il nuovo observer del valore corrente (cold)
        observationLog.add("LiveData: Observer registrato, riceve: $value")
        observer(value)
    }

    fun setValue(newValue: T) {
        value = newValue
        // LiveData notifica solo gli observer registrati
        observers.forEach { observer ->
            observationLog.add("LiveData: Notificato nuovo valore: $newValue")
            observer(value)
        }
    }

    fun getObservationLog(): List<String> = observationLog
}

// Caso d'uso di base: mostriamo come si aggiornano i due approcci.
fun demoP5StateFlowVsLiveData(): List<String> {
    val output = mutableListOf<String>()
    
    // === STATEFLOW: HOT STREAM ===
    output.add("=== StateFlow (HOT Stream) ===")
    val stateFlow = SimpleStateFlowP5("Iniziale")
    stateFlow.update("Primo update")
    stateFlow.update("Secondo update")
    // Ora un collector si iscrive - riceve solo il valore CORRENTE
    stateFlow.collect { value ->
        output.add("StateFlow collector riceve: $value")
    }
    output.addAll(stateFlow.getEmissionLog())
    
    // === LIVEDATA: COLD OBSERVER ===
    output.add("")
    output.add("=== LiveData (COLD Observer) ===")
    val liveData = SimpleLiveDataLikeP5("Iniziale")
    // Observer si registra PRIMA degli update
    liveData.observe { value ->
        output.add("LiveData observer notificato: $value")
    }
    liveData.setValue("Primo update")
    liveData.setValue("Secondo update")
    output.addAll(liveData.getObservationLog())
    
    output.add("")
    output.add("=== Differenza principale ===")
    output.add("StateFlow: hot stream che emette continuamente")
    output.add("LiveData: cold observer che notifica solo gli observer registrati")
    
    return output
}

fun main() {
    val results = demoP5StateFlowVsLiveData()
    results.forEach { println(it) }
}