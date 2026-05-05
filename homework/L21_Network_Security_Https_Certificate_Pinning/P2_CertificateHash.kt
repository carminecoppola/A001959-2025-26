// L21 - P2: hash of the certificato.
// The idea is confrontare l'impronta of the certificato with a valore atteso.

class CertificateHashSimulatorP2 {
    fun calculateHash(certificateData: String): String {
        return certificateData.hashCode().toString()
    }

    fun matchesExpected(certificateData: String, expectedHash: String): Boolean {
        return calculateHash(certificateData) == expectedHash
    }
}

// Basic use case: calcoliamo and verifichiamo a hash.
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