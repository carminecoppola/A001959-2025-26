package com.example.l14viewmodellivedatademo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.l14viewmodellivedatademo.databinding.ActivityMainBinding

/**
 * MainActivity represents the View layer in MVVM.
 *
 * Responsibilities:
 * - inflate and display the layout;
 * - observe LiveData exposed by the ViewModel;
 * - dispatch user events to the ViewModel;
 * - avoid storing business or presentation state directly.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * The ViewModel instance is provided by the Android framework.
     * It survives configuration changes such as screen rotation.
     */
    private val viewModel: CounterViewModel by viewModels()

    companion object {
        private const val TAG = "L14_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            Inflate the XML layout through ViewBinding.
        */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "MainActivity created")

        setupObservers()
        setupListeners()
    }

    /**
     * Observes LiveData from the ViewModel.
     *
     * LiveData is lifecycle-aware: it updates the Activity only when the
     * Activity is in an active lifecycle state.
     */
    private fun setupObservers() {
        viewModel.counter.observe(this) { counterValue ->
            binding.tvCounter.text = counterValue.toString()
        }

        viewModel.errorMessage.observe(this) { message ->
            binding.tvError.text = message ?: ""
        }
    }

    /**
     * Connects user interactions to ViewModel methods.
     *
     * The Activity does not modify the counter directly.
     * It only forwards the event to the ViewModel.
     */
    private fun setupListeners() {
        binding.btnIncrement.setOnClickListener {
            viewModel.increment()
        }

        binding.btnDecrement.setOnClickListener {
            viewModel.decrement()
        }

        binding.btnReset.setOnClickListener {
            viewModel.reset()
        }
    }
}