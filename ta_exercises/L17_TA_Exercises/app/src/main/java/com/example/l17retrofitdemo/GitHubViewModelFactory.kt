package com.example.l17retrofitdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory used to create GitHubViewModel with a Repository dependency.
 *
 * The default ViewModel factory can only create ViewModels with empty constructors.
 */
class GitHubViewModelFactory(
    private val repository: GitHubRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            return GitHubViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}