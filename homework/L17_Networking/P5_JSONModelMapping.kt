// L17 - P5: mapping dei dati JSON in oggetti Kotlin.
// Questo passaggio trasforma la risposta remota in un modello comodo da usare.

data class UserDtoP5(
    val id: Int,
    val name: String,
    val email: String
)

data class UserUiModelP5(
    val displayName: String,
    val contact: String
)

class UserMapperP5 {
    fun toUiModel(dto: UserDtoP5): UserUiModelP5 {
        return UserUiModelP5(
            displayName = dto.name,
            contact = dto.email
        )
    }
}

// Caso d'uso di base: convertiamo un DTO in un modello pronto per la UI.
fun demoL17P5JSONModelMapping(): UserUiModelP5 {
    val dto = UserDtoP5(1, "Luca", "luca@example.com")
    return UserMapperP5().toUiModel(dto)
}