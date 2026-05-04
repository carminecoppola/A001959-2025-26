package com.example.l10layoutsidemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.l10layoutsidemo.databinding.ActivityMainBinding

/**
 * MainActivity — Layouts & UI Demo.
 *
 * This Activity demonstrates:
 * - ConstraintLayout;
 * - TextView, EditText and Button widgets;
 * - event handling with setOnClickListener;
 * - input validation with Kotlin null-safety.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "L10_UI_DEMO"
        private const val VALID_USERNAME = "admin"
        private const val VALID_PASSWORD = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate called")

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            validateLoginForm()
        }

        binding.btnClear.setOnClickListener {
            clearForm()
        }
    }

    private fun validateLoginForm() {
        val username = binding.etUsername.text?.toString()?.trim()
        val password = binding.etPassword.text?.toString()?.trim()

        if (username.isNullOrEmpty()) {
            binding.etUsername.error = "Username required"
            binding.tvResult.text = "Please enter a username."
            return
        }

        if (password.isNullOrEmpty()) {
            binding.etPassword.error = "Password required"
            binding.tvResult.text = "Please enter a password."
            return
        }

        if (username == VALID_USERNAME && password == VALID_PASSWORD) {
            binding.tvResult.text = "Login SUCCESS"
        } else {
            binding.tvResult.text = "Login FAILED"
        }

        Log.d(TAG, "Login attempt with username=$username")
    }

    private fun clearForm() {
        binding.etUsername.text?.clear()
        binding.etPassword.text?.clear()
        binding.tvResult.text = "Result will appear here"
    }
}