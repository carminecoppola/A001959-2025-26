// Exercise P3: separazione dei livelli MVVM.
// The goal is to show how Model, ViewModel, and View can be separated.

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
        // The ViewModel prepares data in a form that is easy to display in the UI.
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

// Basic use case: the ViewModel gets data from the repository and the View displays it.
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