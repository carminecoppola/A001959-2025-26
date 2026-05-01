package com.example.e01_helloworld

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import com.example.e01_helloworld.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        restoreGreeting(savedInstanceState)
    }

    private fun setupListeners() {
        binding.btnGreet.setOnClickListener {
            greetUser()
        }

        binding.etName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                greetUser()
                true
            } else {
                false
            }
        }
    }

    private fun greetUser() {
        val name = binding.etName.text.toString().trim()

        val greeting = if (name.isBlank()) {
            getString(R.string.default_greeting)
        } else {
            getString(R.string.personal_greeting, name)
        }

        binding.tvGreeting.text = greeting
        hideKeyboard()
    }

    private fun restoreGreeting(savedInstanceState: Bundle?) {
        val savedGreeting = savedInstanceState?.getString(KEY_GREETING)
        if (savedGreeting != null) {
            binding.tvGreeting.text = savedGreeting
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_GREETING, binding.tvGreeting.text.toString())
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etName.windowToken, 0)
        binding.etName.clearFocus()
    }

    companion object {
        private const val KEY_GREETING = "key_greeting"
    }
}