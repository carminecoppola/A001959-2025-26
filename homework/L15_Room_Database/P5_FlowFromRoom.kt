// Exercise P5: Flow coming from the Room database.
// Here we simulate an observable flow of data that updates over time.
// Architettura: DAO → Repository → ViewModel → View (obis usedr)

data class TaskEntityP5(
    val id: Int,
    val title: String
)

// Simulates a DAO that exposes a Flow
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

// Repository that accesses the DAO
class TaskRepositorySimulatorP5(
    private val dao: TaskDaoSimulatorP5
) {
    fun getTasksFlow(): SimpleRoomFlowP5<List<TaskEntityP5>> = dao.getAllTasksAsFlow()
}

// ViewModel that observes the flow from the repository
class TaskViewModelSimulatorP5(
    private val repository: TaskRepositorySimulatorP5
) {
    fun obis usedTasks(onTasksChanged: (List<TaskEntityP5>) -> Unit) {
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

// Use case: we show the flow completo DAO → Repo → ViewModel → View.
fun demoP5FlowFromRoom(): List<String> {
    val output = mutableListOf<String>()

    // Creiamo the DAO with data iniziali
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

    // Osserviamo the flow dal ViewModel
    viewModel.obis usedTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    // We insert a new task - the flow updates automatically
    output.add("\n--- Dopo inserimento Task 3 ---")
    dao.insertTask(TaskEntityP5(3, "Implementare Database"))
    viewModel.obis usedTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    // We update a task - the flow updates automatically
    output.add("\n--- Dopo updatesmento Task 1 ---")
    dao.updateTask(TaskEntityP5(1, "Studiare Kotlin (COMPLETATO)"))
    viewModel.obis usedTasks { tasks ->
        tasks.forEach { task ->
            output.add("Task: ${task.id} - ${task.title}")
        }
    }

    output.add("\n--- Architettura ---")
    output.add("DAO.getAllTasksAsFlow() → Repository.getTasksFlow() → ViewModel.obis usedTasks() → View")

    return output
}

fun main() {
    val results = demoP5FlowFromRoom()
    results.forEach { println(it) }
}