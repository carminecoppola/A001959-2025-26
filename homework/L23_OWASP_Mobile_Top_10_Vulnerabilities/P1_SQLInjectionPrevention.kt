// L23 - P1: prevenzione SQL injection.
// L'obiettivo e evitare query costruite concatenando testo non fidato.

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
        OwaspQuestionP1("M1", "Improper Credential Usage", "A", "Le credenziali non vanno hardcodate nel client."),
        OwaspQuestionP1("M2", "Inadequate Supply Chain Security", "B", "Le dipendenze devono essere verificate e aggiornate."),
        OwaspQuestionP1("M3", "Insecure Authentication/Authorization", "C", "Serve un controllo robusto di sessione e ruoli."),
        OwaspQuestionP1("M4", "Insufficient Input/Output Validation", "A", "Input non validati portano a injection e parsing bug."),
        OwaspQuestionP1("M5", "Insecure Communication", "B", "TLS e certificate validation sono obbligatori.")
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

// Caso d'uso di base: costruiamo una query sicura con parametro separato.
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