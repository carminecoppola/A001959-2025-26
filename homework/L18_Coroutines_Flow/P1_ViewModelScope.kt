// L18 - P1: uso di viewModelScope.
// In Android viewModelScope permette di lanciare coroutine legate al ciclo di vita of the ViewModel.
// Here we simulate async operations with a simple delay.

class ViewModelScopeSimulatorP1 {
    private val launchedTasks = mutableListOf<String>()

    // We simulate a launch {} that runs an async operation
    fun launchTask(taskName: String, delayMs: Long = 100) {
        launchedTasks.add("Avviato: $taskName")
        // Simuliamo delay di I/O
        Thread.sleep(delayMs)
        launchedTasks.add("Completato: $taskName")
    }

    fun getTasks(): List<String> = launchedTasks.toList()
}

// Basic use case: we start two tasks in the ViewModel.
fun demoL18P1ViewModelScope(): List<String> {
    val viewModel = ViewModelScopeSimulatorP1()
    viewModel.launchTask("Caricamento data", 50)
    viewModel.launchTask("Aggiornamento UI", 50)
    return viewModel.getTasks()
}

fun main() {
    println("=== viewModelScope ===")
    val tasks = demoL18P1ViewModelScope()
    tasks.forEach { println(it) }
}