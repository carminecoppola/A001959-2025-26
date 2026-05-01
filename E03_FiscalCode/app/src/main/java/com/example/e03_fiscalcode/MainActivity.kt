package com.example.e03_fiscalcode

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.e03_fiscalcode.FiscalCodeViewModel.UiState
import com.example.e03_fiscalcode.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FiscalCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupButton()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is UiState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvResult.visibility = View.GONE
                    binding.btnCompute.isEnabled = false
                }
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvResult.visibility = View.VISIBLE
                    binding.tvResult.setTextColor(getColor(android.R.color.holo_green_dark))
                    binding.tvResult.text = state.code
                    binding.btnCompute.isEnabled = true
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvResult.visibility = View.VISIBLE
                    binding.tvResult.setTextColor(getColor(android.R.color.holo_red_dark))
                    binding.tvResult.text = state.msg
                    binding.btnCompute.isEnabled = true
                }
            }
        }
    }

    private fun setupButton() {
        binding.btnCompute.setOnClickListener {
            val sex = if (binding.rbMale.isChecked) "M" else "F"
            viewModel.compute(
                surname   = binding.etSurname.text.toString().trim(),
                name      = binding.etName.text.toString().trim(),
                birthdate = binding.etBirthdate.text.toString().trim(),
                sex       = sex,
                birthplace = binding.etBirthplace.text.toString().trim()
            )
        }
    }
}