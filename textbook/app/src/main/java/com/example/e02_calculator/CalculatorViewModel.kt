package com.example.e02_calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorViewModel : ViewModel() {

    private val _display = MutableLiveData("0")
    val display: LiveData<String> = _display

    private var accumulator: BigDecimal? = null
    private var pendingOperator: Operator? = null
    private var currentInput = "0"
    private var isTypingNewNumber = false
    private var hasError = false

    fun onDigitPressed(digit: String) {
        if (hasError) clear()

        currentInput = when {
            isTypingNewNumber -> {
                isTypingNewNumber = false
                digit
            }
            currentInput == "0" -> digit
            currentInput == "-0" -> "-$digit"
            currentInput.length >= MAX_INPUT_LENGTH -> currentInput
            else -> currentInput + digit
        }

        updateDisplay(currentInput)
    }

    fun onDecimalPressed() {
        if (hasError) clear()

        if (isTypingNewNumber) {
            currentInput = "0."
            isTypingNewNumber = false
        } else if (!currentInput.contains(".") && currentInput.length < MAX_INPUT_LENGTH) {
            currentInput += "."
        }

        updateDisplay(currentInput)
    }

    fun onOperatorPressed(operator: Operator) {
        if (hasError) return

        val currentValue = currentInput.toBigDecimalOrNull() ?: return

        if (accumulator == null) {
            accumulator = currentValue
        } else if (!isTypingNewNumber) {
            val result = calculate(accumulator ?: BigDecimal.ZERO, currentValue, pendingOperator)
            if (result == null) {
                showError()
                return
            }
            accumulator = result
            currentInput = format(result)
            updateDisplay(currentInput)
        }

        pendingOperator = operator
        isTypingNewNumber = true
    }

    fun onEqualsPressed() {
        if (hasError) return

        val left = accumulator ?: return
        val right = currentInput.toBigDecimalOrNull() ?: return
        val operator = pendingOperator ?: return

        val result = calculate(left, right, operator)
        if (result == null) {
            showError()
            return
        }

        val formatted = format(result)
        currentInput = formatted
        accumulator = null
        pendingOperator = null
        isTypingNewNumber = true
        updateDisplay(formatted)
    }

    fun onToggleSignPressed() {
        if (hasError) return
        if (currentInput == "0") return

        currentInput = if (currentInput.startsWith("-")) {
            currentInput.removePrefix("-")
        } else {
            "-$currentInput"
        }

        updateDisplay(currentInput)
    }

    fun onPercentPressed() {
        if (hasError) return

        val value = currentInput.toBigDecimalOrNull() ?: return
        val result = value.divide(BigDecimal("100"), SCALE, RoundingMode.HALF_UP)
        currentInput = format(result)
        updateDisplay(currentInput)
    }

    fun onBackspacePressed() {
        if (hasError) {
            clear()
            return
        }

        if (isTypingNewNumber) return

        currentInput = when {
            currentInput.length <= 1 -> "0"
            currentInput.length == 2 && currentInput.startsWith("-") -> "0"
            else -> currentInput.dropLast(1)
        }

        updateDisplay(currentInput)
    }

    fun clear() {
        accumulator = null
        pendingOperator = null
        currentInput = "0"
        isTypingNewNumber = false
        hasError = false
        updateDisplay(currentInput)
    }

    private fun calculate(
        left: BigDecimal,
        right: BigDecimal,
        operator: Operator?
    ): BigDecimal? {
        return when (operator) {
            Operator.ADD -> left + right
            Operator.SUBTRACT -> left - right
            Operator.MULTIPLY -> left * right
            Operator.DIVIDE -> {
                if (right.compareTo(BigDecimal.ZERO) == 0) {
                    null
                } else {
                    left.divide(right, SCALE, RoundingMode.HALF_UP)
                }
            }
            null -> right
        }
    }

    private fun showError() {
        hasError = true
        accumulator = null
        pendingOperator = null
        currentInput = "0"
        _display.value = "Error"
    }

    private fun updateDisplay(value: String) {
        _display.value = value
    }

    private fun format(value: BigDecimal): String {
        return value
            .stripTrailingZeros()
            .toPlainString()
            .let { result ->
                if (result == "-0") "0" else result
            }
            .let { result ->
                if (result.length > MAX_DISPLAY_LENGTH) {
                    result.take(MAX_DISPLAY_LENGTH)
                } else {
                    result
                }
            }
    }

    enum class Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    companion object {
        private const val MAX_INPUT_LENGTH = 15
        private const val MAX_DISPLAY_LENGTH = 18
        private const val SCALE = 10
    }
}
