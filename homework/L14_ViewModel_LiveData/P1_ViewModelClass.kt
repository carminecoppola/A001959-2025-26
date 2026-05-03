// Esercizio P1: classe ViewModel semplice.
// Qui simuliamo il ruolo del ViewModel: conservare e preparare i dati per la UI.

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

// Caso d'uso di base: incrementiamo un valore e leggiamo lo stato aggiornato.
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