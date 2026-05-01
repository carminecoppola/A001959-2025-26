package com.example.lifecyclecounterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.lifecyclecounterapp.databinding.ActivityMainBinding

/**
 * MainActivity demonstrates the Android Activity Lifecycle.
 *
 * This Activity is intentionally simple and academic:
 * - it logs every lifecycle callback;
 * - it updates the UI when the state changes;
 * - it uses ViewModel to survive screen rotation;
 * - it uses onSaveInstanceState for lightweight state preservation.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val counterViewModel: CounterViewModel by viewModels()

    private var lastLifecycleCallback: String = "onCreate"

    companion object {
        private const val TAG = "LIFECYCLE_DEMO"
        private const val KEY_LAST_CALLBACK = "key_last_callback"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lastLifecycleCallback =
            savedInstanceState?.getString(KEY_LAST_CALLBACK) ?: "onCreate"

        logAndDisplay("onCreate")

        binding.incrementButton.setOnClickListener {
            counterViewModel.increment()
            updateCounterText()
        }

        binding.decrementButton.setOnClickListener {
            counterViewModel.decrement()
            updateCounterText()
        }

        binding.resetButton.setOnClickListener {
            counterViewModel.reset()
            updateCounterText()
        }

        updateCounterText()
    }

    override fun onStart() {
        super.onStart()
        logAndDisplay("onStart")
    }

    override fun onResume() {
        super.onResume()
        logAndDisplay("onResume")
    }

    override fun onPause() {
        super.onPause()
        logAndDisplay("onPause")
    }

    override fun onStop() {
        super.onStop()
        logAndDisplay("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_LAST_CALLBACK, lastLifecycleCallback)

        Log.d(TAG, "onSaveInstanceState called")
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounterText() {
        binding.counterTextView.text = "Counter: ${counterViewModel.counter}"
    }

    @SuppressLint("SetTextI18n")
    private fun logAndDisplay(callbackName: String) {
        lastLifecycleCallback = callbackName
        Log.d(TAG, "$callbackName called")

        if (::binding.isInitialized) {
            binding.lifecycleTextView.text = "Last callback: $callbackName"
        }
    }
}