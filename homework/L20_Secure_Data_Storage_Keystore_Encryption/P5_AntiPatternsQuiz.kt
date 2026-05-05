// L20 - P5: quiz about data security anti-patterns.
// Each question highlights a common mistake to avoid.

data class QuizQuestionP5(
    val question: String,
    val correctAnswer: String
)

class SecurityAntiPatternsQuizP5 {
    private val questions = listOf(
        QuizQuestionP5("Where should plaintext passwords never be stored?", "SharedPreferences"),
        QuizQuestionP5("Cosa and rischioso for sensitive data?", "allowBackup"),
        QuizQuestionP5("Quale pratica must be evitata for i segreti?", "Hardcoding")
    )

    fun runQuiz(answers: List<String>): List<String> {
        return questions.mapIndexed { index, question ->
            val userAnswer = answers.getOrNull(index) ?: ""
            if (userAnswer.equals(question.correctAnswer, ignoreCase = true)) {
                "Corretto: ${question.question}"
            } else {
                "Sbagliato: ${question.question}"
            }
        }
    }
}

// Basic use case: rispondiamo al quiz with alcune risposte corrette and sbagliate.
fun demoL20P5AntiPatternsQuiz(): List<String> {
    val quiz = SecurityAntiPatternsQuizP5()
    return quiz.runQuiz(listOf("SharedPreferences", "allowBackup", "Logger"))
}

fun main() {
    println("=== Security Anti-Patterns Quiz ===")
    val results = demoL20P5AntiPatternsQuiz()
    results.forEach { println(it) }
}