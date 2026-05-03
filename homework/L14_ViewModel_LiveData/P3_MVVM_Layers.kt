// Esercizio P3: separazione dei livelli MVVM.
// L'obiettivo e mostrare come Model, ViewModel e View possano essere separati.

data class ProductP3(
    val id: Int,
    val name: String,
    val price: Double
)

class ProductRepositoryP3 {
    fun getProducts(): List<ProductP3> {
        return listOf(
            ProductP3(1, "Notebook", 4.50),
            ProductP3(2, "Penna", 1.20)
        )
    }
}

class ProductViewModelP3(
    private val repository: ProductRepositoryP3
) {
    fun loadProducts(): List<String> {
        // Il ViewModel prepara i dati in una forma facile da mostrare nella UI.
        return repository.getProducts().map { product ->
            "${product.name} - EUR ${product.price}"
        }
    }
}

class ProductViewP3 {
    fun render(lines: List<String>): String {
        return lines.joinToString(separator = "\n")
    }
}

// Caso d'uso di base: il ViewModel prende i dati dal repository e la View li mostra.
fun demoP3MVVMLayers(): String {
    val repository = ProductRepositoryP3()
    val viewModel = ProductViewModelP3(repository)
    val view = ProductViewP3()

    return view.render(viewModel.loadProducts())
}

fun main() {
    val result = demoP3MVVMLayers()
    println(result)
}