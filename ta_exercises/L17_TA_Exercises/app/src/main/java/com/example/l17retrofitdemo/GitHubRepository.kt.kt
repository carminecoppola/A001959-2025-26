package com.example.l17retrofitdemo

import retrofit2.HttpException
import java.io.IOException

/**
 * Repository layer.
 *
 * The Repository hides the networking implementation from the ViewModel.
 * It also centralizes error handling.
 */
class GitHubRepository(
    private val api: GitHubApiService
) {

    /**
     * Loads a GitHub user and wraps the result in Kotlin Result.
     *
     * This follows the lesson pattern:
     * - successful HTTP response -> Result.success(...)
     * - HTTP 4xx/5xx error -> Result.failure(...)
     * - network error or timeout -> Result.failure(...)
     */
    suspend fun getUser(username: String): Result<GitHubUser> {
        return try {
            val user = api.getUserByUsername(username)
            Result.success(user)
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "User not found."
                401 -> "Unauthorized request."
                403 -> "Forbidden or rate limit exceeded."
                in 500..599 -> "Server error. Please try again later."
                else -> "HTTP ${e.code()}: ${e.message()}"
            }

            Result.failure(Exception(message))
        } catch (e: IOException) {
            Result.failure(Exception("Network error. Check your connection."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Unknown error."))
        }
    }
}