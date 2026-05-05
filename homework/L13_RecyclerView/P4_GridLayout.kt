// Exercise P4: using a grid to arrange items.
// In Android, a GridLayoutManager arranges items across multiple columns.

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
            "The number of columns must be greater than zero"
        }
    }

    // We use GridLayoutManager to arrange items in a 2-column grid.
    // Here we simulate that behavior by grouping items per row.
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

// Basic use case: we distribute items in a 2-column grid.
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