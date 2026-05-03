// L21 - P2: hash del certificato.
// L'idea e confrontare l'impronta del certificato con un valore atteso.

class CertificateHashSimulatorP2 {
    fun calculateHash(certificateData: String): String {
        return certificateData.hashCode().toString()
    }

    fun matchesExpected(certificateData: String, expectedHash: String): Boolean {
        return calculateHash(certificateData) == expectedHash
    }
}

// Caso d'uso di base: calcoliamo e verifichiamo un hash.
fun demoL21P2CertificateHash(): List<String> {
    val simulator = CertificateHashSimulatorP2()
    val certData = "fake-certificate-data"
    val hash = simulator.calculateHash(certData)
    return listOf(
        hash,
        "Hash valido: ${simulator.matchesExpected(certData, hash)}"
    )
}

fun main() {
    println("=== Certificate Hash ===")
    val results = demoL21P2CertificateHash()
    println("Calcolato: ${results[0]}")
    println(results[1])
}