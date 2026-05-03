// L24 - P1: diagramma dell'architettura.
// L'output e una rappresentazione testuale del flusso tra i componenti principali.

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

// Caso d'uso di base: generiamo un diagramma testuale semplice.
fun demoL24P1ArchitectureDiagram(): String {
    return ArchitectureDiagramSimulatorP1().render()
}

fun main() {
    println("=== Architecture Diagram ===")
    println(demoL24P1ArchitectureDiagram())
}