package com.example.l17retrofitdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * GitHubViewModel contains presentation logic.
 *
 * The Activity sends user events to this ViewModel.
 * The ViewModel calls the Repository and exposes a UiState to the Activity.
 */
class GitHubViewModel(
    private val repository: GitHubRepository
) : ViewModel() {

    /**
     * Backing property pattern.
     * Only the ViewModel can modify the MutableLiveData.
     */
    private val _uiState = MutableLiveData<UiState>(UiState.Idle)

    /**
     * Public read-only LiveData observed by the Activity.
     */
    val uiState: LiveData<UiState> = _uiState

    /**
     * Searches a GitHub user by username.
     *
     * Empty input is handled before performing any network request.
     */
    fun searchUser(username: String) {
        val cleanUsername = username.trim()

        if (cleanUsername.isEmpty()) {
            _uiState.value = UiState.Error("Username required.")
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = repository.getUser(cleanUsername)

            _uiState.value = result.fold(
                onSuccess = { user -> UiState.Success(user) },
                onFailure = { error ->
                    UiState.Error(error.message ?: "Unknown error.")
                }
            )
        }
    }
}