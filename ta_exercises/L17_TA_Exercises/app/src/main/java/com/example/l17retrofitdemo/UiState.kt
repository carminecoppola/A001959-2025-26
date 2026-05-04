package com.example.l17retrofitdemo

/**
 * UiState represents all possible states of the screen.
 *
 * This sealed class makes UI rendering explicit and exhaustive:
 * - Idle: no request started yet;
 * - Loading: request in progress;
 * - Success: data available;
 * - Error: request failed.
 */
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val user: GitHubUser) : UiState()
    data class Error(val message: String) : UiState()
}