// L17 - P5: mapping dei data JSON in oggetti Kotlin.
// This step transforms the remote response into a model that is easy to use.

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

// Basic use case: we convert a DTO into a UI-ready model.
fun demoL17P5JSONModelMapping(): UserUiModelP5 {
    val dto = UserDtoP5(1, "Luca", "luca@example.com")
    return UserMapperP5().toUiModel(dto)
}

fun main() {
    println("=== JSON Model Mapping ===")
    val uiModel = demoL17P5JSONModelMapping()
    println("Display Name: ${uiModel.displayName}")
    println("Contact: ${uiModel.contact}")
}