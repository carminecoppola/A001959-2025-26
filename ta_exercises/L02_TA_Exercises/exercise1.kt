

// Exercise 1 - If statement as expression - Rewrite a C-style if/else as a Kotlin expression
// and assign the result to a val

fun main() {
    val score = 85

    // In Kotlin, 'if' non è solo uno statement (come in Java/C),
    // ma è una vera ESPRESSIONE: produce un valore che può essere
    // assegnato direttamente a una variabile.
    val grade = if (score >= 90) "A"
                else if (score >= 80) "B"
                else if (score >= 70) "C"
                else "F"

    // Quando 'if' è usato come espressione, il ramo 'else' è OBBLIGATORIO,
    // perché il compilatore deve garantire che venga sempre prodotto un valore.

    println("Grade: $grade")  // Output: Grade: B
}