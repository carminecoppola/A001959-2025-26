// Esercizio P4: uso di una griglia per disporre gli elementi.
// In Android un GridLayoutManager organizza gli item su piu colonne.

data class GridItemP4(
    val id: Int,
    val label: String
)

data class GridLayoutConfigP4(
    val spanCount: Int
)

class GridLayoutManagerSimulatorP4(
    private val config: GridLayoutConfigP4
) {
    init {
        require(config.spanCount > 0) {
            "Il numero di colonne deve essere maggiore di zero"
        }
    }

    // Utilizziamo GridLayoutManager per disporre gli elementi in una griglia con 2 colonne.
    // Qui simuliamo quel comportamento raggruppando gli elementi per riga.
    fun layout(items: List<GridItemP4>): List<List<GridItemP4>> {
        val rows = mutableListOf<List<GridItemP4>>()

        for (index in items.indices step config.spanCount) {
            val row = items.subList(index, minOf(index + config.spanCount, items.size))
            rows.add(row)
        }

        return rows
    }

    fun renderGrid(items: List<GridItemP4>): List<String> {
        val rows = layout(items)
        return rows.mapIndexed { rowIndex, row ->
            "Riga ${rowIndex + 1}: ${row.joinToString { it.label }}"
        }
    }
}

// Caso d'uso di base: distribuiamo gli elementi in una griglia da 2 colonne.
fun demoP4GridLayout(): List<String> {
    val items = listOf(
        GridItemP4(1, "A"),
        GridItemP4(2, "B"),
        GridItemP4(3, "C"),
        GridItemP4(4, "D"),
        GridItemP4(5, "E")
    )

    val gridManager = GridLayoutManagerSimulatorP4(GridLayoutConfigP4(spanCount = 2))
    return gridManager.renderGrid(items)
}

fun main() {
    val results = demoP4GridLayout()
    results.forEach { println(it) }
}