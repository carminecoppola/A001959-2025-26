/*
Problem 3 - User Profile Formatter

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P3_UserProfileFormatter.kt -include-runtime -d P3_UserProfileFormatter.jar
2. Run the program:
   java -jar P3_UserProfileFormatter.jar
*/

data class User(val name: String, val bio: String?, val website: String?)

fun format(user: User): String =
    "Name: ${user.name}\nBio: ${user.bio ?: "N/A"}\nWebsite: ${user.website ?: "N/A"}"

fun main() {
    println(format(User("Alice", null, "https://alice.dev")))
}