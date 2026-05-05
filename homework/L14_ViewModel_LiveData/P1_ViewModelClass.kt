// Exercise P1: simple ViewModel class.
// Here we simulate the ruolo of the ViewModel: conservare and preparare i data for the UI.

data class UiStateP1(
    val counter: Int,
    val message: String
)

class SimpleViewModelP1 {
    private var counter: Int = 0

    fun increment(): UiStateP1 {
        counter += 1
        return currentState()
    }

    fun currentState(): UiStateP1 {
        return UiStateP1(
            counter = counter,
            message = "Valore corrente: $counter"
        )
    }
}

// Basic use case: we increment a value and read the updated state.
fun demoP1ViewModelClass(): List<UiStateP1> {
    val viewModel = SimpleViewModelP1()
    return listOf(
        viewModel.currentState(),
        viewModel.increment(),
        viewModel.increment()
    )
}

fun main() {
    val results = demoP1ViewModelClass()
    results.forEach { println(it) }
}