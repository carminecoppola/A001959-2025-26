// L18 - P1: uso di viewModelScope.
// In Android viewModelScope permette di lanciare coroutine legate al ciclo di vita del ViewModel.
// Qui simuliamo operazioni asincrone con un semplice delay.

class ViewModelScopeSimulatorP1 {
    private val launchedTasks = mutableListOf<String>()

    // Simuliamo un launch {} che esegue un'operazione asincrona
    fun launchTask(taskName: String, delayMs: Long = 100) {
        launchedTasks.add("Avviato: $taskName")
        // Simuliamo delay di I/O
        Thread.sleep(delayMs)
        launchedTasks.add("Completato: $taskName")
    }

    fun getTasks(): List<String> = launchedTasks.toList()
}

// Caso d'uso di base: avviamo due attività nel ViewModel.
fun demoL18P1ViewModelScope(): List<String> {
    val viewModel = ViewModelScopeSimulatorP1()
    viewModel.launchTask("Caricamento dati", 50)
    viewModel.launchTask("Aggiornamento UI", 50)
    return viewModel.getTasks()
}

fun main() {
    println("=== viewModelScope ===")
    val tasks = demoL18P1ViewModelScope()
    tasks.forEach { println(it) }
}