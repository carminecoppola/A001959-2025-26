// L20 - P5: quiz sugli anti-pattern di sicurezza dei dati.
// Ogni domanda evidenzia un errore comune da evitare.

data class QuizQuestionP5(
    val question: String,
    val correctAnswer: String
)

class SecurityAntiPatternsQuizP5 {
    private val questions = listOf(
        QuizQuestionP5("Dove non bisogna salvare password in chiaro?", "SharedPreferences"),
        QuizQuestionP5("Cosa e rischioso per dati sensibili?", "allowBackup"),
        QuizQuestionP5("Quale pratica va evitata per i segreti?", "Hardcoding")
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

// Caso d'uso di base: rispondiamo al quiz con alcune risposte corrette e sbagliate.
fun demoL20P5AntiPatternsQuiz(): List<String> {
    val quiz = SecurityAntiPatternsQuizP5()
    return quiz.runQuiz(listOf("SharedPreferences", "allowBackup", "Logger"))
}

fun main() {
    println("=== Security Anti-Patterns Quiz ===")
    val results = demoL20P5AntiPatternsQuiz()
    results.forEach { println(it) }
}