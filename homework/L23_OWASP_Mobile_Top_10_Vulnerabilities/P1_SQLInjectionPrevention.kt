// L23 - P1: prevenzione SQL injection.
// The goal is to avoid queries built by concatenating untrusted text.

data class QueryP1(
    val sql: String,
    val parameters: List<String>
)

class SQLInjectionPreventionSimulatorP1 {
    fun buildSafeQuery(userId: String): QueryP1 {
        return QueryP1(
            sql = "SELECT * FROM users WHERE id = ?",
            parameters = listOf(userId)
        )
    }
}

data class OwaspQuestionP1(
    val id: String,
    val risk: String,
    val correctAnswer: String,
    val explanation: String
)

data class OwaspQuizResultP1(
    val score: Int,
    val total: Int,
    val feedback: List<String>
)

class OwaspTop5QuizSimulatorP1 {
    private val questions = listOf(
        OwaspQuestionP1("M1", "Improper Credential Usage", "A", "Credentials must not be hardcoded in the client."),
        OwaspQuestionP1("M2", "Inadequate Supply Chain Security", "B", "Dependencies must be verified and updated."),
        OwaspQuestionP1("M3", "Insecure Authentication/Authorization", "C", "Robust session and role control is required."),
        OwaspQuestionP1("M4", "Insufficient Input/Output Validataon", "A", "Unvalidated input leads to injection and parsing bugs."),
        OwaspQuestionP1("M5", "Insecure Communication", "B", "TLS and certificate validataon sono obbligatori.")
    )

    fun evaluate(answers: Map<String, String>): OwaspQuizResultP1 {
        val feedback = mutableListOf<String>()
        var score = 0

        for (q in questions) {
            val userAnswer = answers[q.id]
            if (userAnswer == q.correctAnswer) {
                score++
                feedback.add("${q.id}: corretto - ${q.explanation}")
            } else {
                feedback.add("${q.id}: errato - ${q.explanation}")
            }
        }

        return OwaspQuizResultP1(score = score, total = questions.size, feedback = feedback)
    }
}

// Basic use case: we build a secure query with a separate parameter.
fun demoL23P1SQLInjectionPrevention(): QueryP1 {
    return SQLInjectionPreventionSimulatorP1().buildSafeQuery("42")
}

fun demoL23P1OwaspQuiz(): OwaspQuizResultP1 {
    val quiz = OwaspTop5QuizSimulatorP1()
    val sampleAnswers = mapOf(
        "M1" to "A",
        "M2" to "B",
        "M3" to "A",
        "M4" to "A",
        "M5" to "B"
    )
    return quiz.evaluate(sampleAnswers)
}

fun main() {
    println("=== SQL Injection Prevention ===")
    val query = demoL23P1SQLInjectionPrevention()
    println(query.sql)
    println(query.parameters)

    println("=== OWASP M1-M5 Quiz ===")
    val quizResult = demoL23P1OwaspQuiz()
    println("Score: ${quizResult.score}/${quizResult.total}")
    quizResult.feedback.forEach { println(it) }
}