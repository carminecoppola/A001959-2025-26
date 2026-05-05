package com.example.l18flowcounterdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.l18flowcounterdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

/**
 * MainActivity represents the View layer.
 *
 * It demonstrates:
 * - lifecycleScope for launching UI-related coroutines;
 * - repeatOnLifecycle for lifecycle-safe Flow collection;
 * - StateFlow collection for persistent UI state;
 * - SharedFlow collection for one-shot UI events.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * ViewModel is created and retained by the Android framework.
     * It survives configuration changes such as screen rotation.
     */
    private val viewModel: CounterViewModel by viewModels()

    companion object {
        private const val TAG = "L18_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            Inflate the layout through ViewBinding.
        */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "MainActivity created")

        setupListeners()
        collectFlows()
    }

    /**
     * Registers button click listeners.
     *
     * The Activity does not modify the state directly.
     * It delegates user actions to the ViewModel.
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

        binding.btnBackgroundWork.setOnClickListener {
            viewModel.simulateBackgroundWork()
        }
    }

    /**
     * Collects StateFlow and SharedFlow safely.
     *
     * repeatOnLifecycle(Lifecycle.State.STARTED) automatically:
     * - starts collection when the Activity becomes visible;
     * - stops collection when the Activity goes to the background;
     * - restarts collection when the Activity returns to the foreground.
     */
    private fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                /*
                    Collect StateFlow.
                    This represents continuous UI state.
                */
                launch {
                    viewModel.count.collect { count ->
                        binding.tvCount.text = count.toString()
                        binding.tvStateDescription.text =
                            "StateFlow current value: $count"
                    }
                }

                /*
                    Collect SharedFlow.
                    This represents one-shot events.
                */
                launch {
                    viewModel.events.collect { event ->
                        when (event) {
                            is UiEvent.ShowMessage -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    event.message,
                                    Toast.LENGTH_SHORT
                                ).show()

                                binding.tvLastEvent.text =
                                    "Last SharedFlow event: ${event.message}"
                            }
                        }
                    }
                }
            }
        }
    }
}