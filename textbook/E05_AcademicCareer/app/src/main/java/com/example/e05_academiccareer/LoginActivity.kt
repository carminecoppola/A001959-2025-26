package com.example.e05_academiccareer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.e05_academiccareer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm: CareerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.state.observe(this) { state ->
            when (state) {
                is CareerViewModel.UiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.tvError.isVisible     = false
                    binding.btnLogin.isEnabled    = false
                }
                is CareerViewModel.UiState.Success -> {
                    startActivity(Intent(this, CareerActivity::class.java))
                    finish()
                }
                is CareerViewModel.UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.tvError.isVisible     = true
                    binding.tvError.text          = state.msg
                    binding.btnLogin.isEnabled    = true
                }
                else -> {
                    binding.progressBar.isVisible = false
                    binding.btnLogin.isEnabled    = true
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            vm.login(
                userId   = binding.etMatricola.text.toString().trim(),
                password = binding.etPassword.text.toString()
            )
        }

        vm.checkExistingSession()
    }
}