// Esercizio P5: Flow che arriva dal database Room.
// Qui simuliamo un flusso osservabile di dati che si aggiorna nel tempo.
// Architettura: DAO → Repository → ViewModel → View (observer)

data class TaskEntityP5(
    val id: Int,
    val title: String
)

// Simula un DAO che espone un Flow
class TaskDaoSimulatorP5(
    private val tasks: MutableList<TaskEntityP5>
) {
    private val taskFlow = SimpleRoomFlowP5(tasks.toList())

    fun getAllTasksAsFlow(): SimpleRoomFlowP5<List<TaskEntityP5>> = taskFlow

    fun insertTask(task: TaskEntityP5) {
        tasks.add(task)
        taskFlow.update(tasks.toList())
    }

    fun updateTask(task: TaskEntityP5) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
            taskFlow.update(tasks.toList())
        }
    }
}

// Repository che accede al DAO
class TaskRepositorySimulatorP5(
    private val dao: TaskDaoSimulatorP5
) {
    fun getTasksFlow(): SimpleRoomFlowP5<List<TaskEntityP5>> = dao.getAllTasksAsFlow()
}

// ViewModel che osserva il flow dal Repository
class TaskViewModelSimulatorP5(
    private val repository: TaskRepositorySimulatorP5
) {
    fun observeTasks(onTasksChanged: (List<TaskEntityP5>) -> Unit) {
        repository.getTasksFlow().collect { tasks ->
            onTasksChanged(tasks)
        }
    }
}

class SimpleRoomFlowP5<T>(initialValue: T) {
    private var value: T = initialValue
    private val collectors = mutableListOf<(T) -> Unit>()

    fun collect(collector: (T) -> Unit) {
        collectors.add(collector)
        collector(value)
    }

    fun update(newValue: T) {
        value = newValue
        collectors.forEach { collector -> collector(value) }
    }
}

// Caso d'uso: mostriamo il flusso completo DAO → Repo → ViewModel → View.
fun demoP5FlowFromRoom(): List<String> {
    val output = mutableListOf<String>()

    // Creiamo il DAO con dati iniziali
    val dao = TaskDaoSimulatorP5(
        mutableListOf(
            TaskEntityP5(1, "Studiare Kotlin"),
            TaskEntityP5(2, "Leggere Room guide")
        )
    )

    val repository = TaskRepositorySimulatorP5(dao)
    val viewModel = TaskViewModelSimulatorP5(repository)

    output.add("=== Flow da Room Database ===")
    output.add("\n--- Stato iniziale ---")

    // Osserviamo il flow dal ViewModel
    viewModel.observeTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    // Inseriamo un nuovo task - il flow si aggiorna automaticamente
    output.add("\n--- Dopo inserimento Task 3 ---")
    dao.insertTask(TaskEntityP5(3, "Implementare Database"))
    viewModel.observeTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    // Aggiorniamo un task - il flow si aggiorna automaticamente
    output.add("\n--- Dopo aggiornamento Task 1 ---")
    dao.updateTask(TaskEntityP5(1, "Studiare Kotlin (COMPLETATO)"))
    viewModel.observeTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    output.add("\n--- Architettura ---")
    output.add("DAO.getAllTasksAsFlow() → Repository.getTasksFlow() → ViewModel.observeTasks() → View")

    return output
}

fun main() {
    val results = demoP5FlowFromRoom()
    results.forEach { println(it) }
}