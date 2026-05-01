package com.example.e02_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.e02_calculator.CalculatorViewModel.Operator
import com.example.e02_calculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupDigitButtons()
        setupOperatorButtons()
        setupActionButtons()
    }

    private fun observeViewModel() {
        viewModel.display.observe(this) { displayValue ->
            binding.tvDisplay.text = displayValue
        }
    }

    private fun setupDigitButtons() {
        val digitButtons = mapOf(
            binding.btn0 to "0",
            binding.btn1 to "1",
            binding.btn2 to "2",
            binding.btn3 to "3",
            binding.btn4 to "4",
            binding.btn5 to "5",
            binding.btn6 to "6",
            binding.btn7 to "7",
            binding.btn8 to "8",
            binding.btn9 to "9"
        )

        digitButtons.forEach { (button, digit) ->
            button.setOnClickListener {
                viewModel.onDigitPressed(digit)
            }
        }
    }

    private fun setupOperatorButtons() {
        binding.btnAdd.setOnClickListener {
            viewModel.onOperatorPressed(Operator.ADD)
        }

        binding.btnSubtract.setOnClickListener {
            viewModel.onOperatorPressed(Operator.SUBTRACT)
        }

        binding.btnMultiply.setOnClickListener {
            viewModel.onOperatorPressed(Operator.MULTIPLY)
        }

        binding.btnDivide.setOnClickListener {
            viewModel.onOperatorPressed(Operator.DIVIDE)
        }
    }

    private fun setupActionButtons() {
        binding.btnDecimal.setOnClickListener {
            viewModel.onDecimalPressed()
        }

        binding.btnEquals.setOnClickListener {
            viewModel.onEqualsPressed()
        }

        binding.btnClear.setOnClickListener {
            viewModel.clear()
        }

        binding.btnToggleSign.setOnClickListener {
            viewModel.onToggleSignPressed()
        }

        binding.btnPercent.setOnClickListener {
            viewModel.onPercentPressed()
        }

        binding.tvDisplay.setOnLongClickListener {
            viewModel.onBackspacePressed()
            true
        }
    }
}

