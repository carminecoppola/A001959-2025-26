// L18 - P1: uso di viewModelScope.
// In Android viewModelScope permette di lanciare coroutine legate al ciclo di vita del ViewModel.

class ViewModelScopeSimulatorP1 {
    private val launchedTasks = mutableListOf<String>()

    // Qui simuliamo un lavoro che verrebbe eseguito nel ViewModelScope.
    fun launchTask(taskName: String) {
        launchedTasks.add("Avviato: $taskName")
    }

    fun getTasks(): List<String> = launchedTasks.toList()
}

// Caso d'uso di base: avviamo due attività nel ViewModel.
fun demoL18P1ViewModelScope(): List<String> {
    val viewModel = ViewModelScopeSimulatorP1()
    viewModel.launchTask("Caricamento dati")
    viewModel.launchTask("Aggiornamento UI")
    return viewModel.getTasks()
}