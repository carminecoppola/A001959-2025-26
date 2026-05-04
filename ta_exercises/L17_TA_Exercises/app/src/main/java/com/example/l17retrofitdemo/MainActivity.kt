package com.example.l17retrofitdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.l17retrofitdemo.databinding.ActivityMainBinding

/**
 * MainActivity represents the View layer.
 *
 * Responsibilities:
 * - display UI;
 * - collect user input;
 * - observe UiState;
 * - render Loading, Success and Error states;
 * - avoid executing networking code directly.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "L17_RETROFIT_DEMO"
    }

    /**
     * Manual dependency creation for a small academic demo.
     *
     * RetrofitInstance -> ApiService -> Repository -> ViewModel
     */
    private val viewModel: GitHubViewModel by viewModels {
        val repository = GitHubRepository(RetrofitInstance.api)
        GitHubViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupObservers()
    }

    /**
     * Registers UI event listeners.
     */
    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val username = binding.etUsername.text?.toString() ?: ""
            viewModel.searchUser(username)
        }
    }

    /**
     * Observes the ViewModel state and renders it.
     */
    private fun setupObservers() {
        viewModel.uiState.observe(this) { state ->
            renderState(state)
        }
    }

    /**
     * Renders all possible UiState values.
     */
    private fun renderState(state: UiState) {
        when (state) {
            UiState.Idle -> {
                binding.progressBar.isVisible = false
                binding.tvError.isVisible = false
                binding.tvResult.text = "Enter a GitHub username and press Search."
            }

            UiState.Loading -> {
                binding.progressBar.isVisible = true
                binding.tvError.isVisible = false
                binding.tvResult.text = "Loading..."
            }

            is UiState.Success -> {
                binding.progressBar.isVisible = false
                binding.tvError.isVisible = false

                val user = state.user

                binding.tvResult.text =
                    """
                    Login: ${user.login}
                    Name: ${user.name ?: "not available"}
                    Bio: ${user.bio ?: "not available"}
                    Public repositories: ${user.publicRepos}
                    Profile: ${user.profileUrl}
                    """.trimIndent()

                Log.d(TAG, "Loaded user: ${user.login}")
            }

            is UiState.Error -> {
                binding.progressBar.isVisible = false
                binding.tvError.isVisible = true
                binding.tvError.text = state.message
                binding.tvResult.text = "Request failed."
                Log.d(TAG, "Error: ${state.message}")
            }
        }
    }
}