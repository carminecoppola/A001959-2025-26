// L24 - P1: architecture diagram.
// The output is a textual representation of the flow between main components.

data class ArchitectureNodeP1(
    val name: String
)

class ArchitectureDiagramSimulatorP1 {
    fun render(): String {
        return """
            App UI -> ViewModel -> Repository -> Data Source
                 |            |             |
                 v            v             v
            Auth Flow     Session Mgmt   Secure Storage
                 |                          |
                 v                          v
              OAuth2 / Biometrics      Encrypted Token Store
        """.trimIndent()
    }
}

// Basic use case: we generate a simple textual diagram.
fun demoL24P1ArchitectureDiagram(): String {
    return ArchitectureDiagramSimulatorP1().render()
}

fun main() {
    println("=== Architecture Diagram ===")
    println(demoL24P1ArchitectureDiagram())
}