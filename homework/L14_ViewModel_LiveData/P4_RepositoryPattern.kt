// Esercizio P4: pattern Repository.
// Il repository nasconde la sorgente dei dati e offre una sola interfaccia alla UI.

data class UserProfileP4(
    val id: Int,
    val name: String,
    val role: String
)

class UserLocalDataSourceP4 {
    fun getUsers(): List<UserProfileP4> {
        return listOf(
            UserProfileP4(1, "Alice", "Student"),
            UserProfileP4(2, "Bob", "Teacher")
        )
    }
}

class UserRepositoryP4(
    private val localDataSource: UserLocalDataSourceP4
) {
    fun loadUsers(): List<UserProfileP4> {
        return localDataSource.getUsers()
    }

    fun loadUserNames(): List<String> {
        return loadUsers().map { user -> user.name }
    }
}

// Caso d'uso di base: leggiamo i nomi dei profili tramite il repository.
fun demoP4RepositoryPattern(): List<String> {
    val repository = UserRepositoryP4(UserLocalDataSourceP4())
    return repository.loadUserNames()
}

fun main() {
    val results = demoP4RepositoryPattern()
    results.forEach { println(it) }
}