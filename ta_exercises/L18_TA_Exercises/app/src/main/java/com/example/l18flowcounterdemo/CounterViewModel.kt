package com.example.l18flowcounterdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * CounterViewModel demonstrates the Flow concepts from Lesson 18.
 *
 * It uses:
 * - StateFlow for persistent UI state;
 * - SharedFlow for one-shot UI events;
 * - viewModelScope for lifecycle-aware coroutine launching.
 *
 * Important:
 * The ViewModel must not hold references to Activity, Fragment, View or Context.
 */
class CounterViewModel : ViewModel() {

    companion object {
        private const val TAG = "L18_VIEWMODEL"
    }

    /**
     * MutableStateFlow is private because only the ViewModel should mutate state.
     *
     * StateFlow always has a current value.
     * New collectors immediately receive the latest value.
     */
    private val _count = MutableStateFlow(0)

    /**
     * Public read-only StateFlow exposed to the Activity.
     */
    val count: StateFlow<Int> = _count.asStateFlow()

    /**
     * MutableSharedFlow is private because only the ViewModel should emit events.
     *
     * SharedFlow is appropriate for one-shot events, not for persistent state.
     */
    private val _events = MutableSharedFlow<UiEvent>()

    /**
     * Public read-only SharedFlow observed by the Activity.
     */
    val events: SharedFlow<UiEvent> = _events

    init {
        /*
            This log is useful for demonstrating that ViewModel survives rotation.
        */
        Log.d(TAG, "CounterViewModel created")
    }

    /**
     * Increments the counter.
     *
     * update { } atomically modifies the current StateFlow value.
     */
    fun increment() {
        _count.update { currentValue ->
            currentValue + 1
        }

        viewModelScope.launch {
            _events.emit(UiEvent.ShowMessage("Counter incremented"))
        }
    }

    /**
     * Decrements the counter.
     *
     * Error handling:
     * The counter is not allowed to go below zero.
     */
    fun decrement() {
        val currentValue = _count.value

        if (currentValue == 0) {
            viewModelScope.launch {
                _events.emit(UiEvent.ShowMessage("Counter cannot be less than zero"))
            }
            return
        }

        _count.update { value ->
            value - 1
        }

        viewModelScope.launch {
            _events.emit(UiEvent.ShowMessage("Counter decremented"))
        }
    }

    /**
     * Resets the counter to zero.
     */
    fun reset() {
        _count.value = 0

        viewModelScope.launch {
            _events.emit(UiEvent.ShowMessage("Counter reset"))
        }
    }

    /**
     * Simulates asynchronous work using a coroutine.
     *
     * delay() suspends the coroutine without blocking the UI thread.
     */
    fun simulateBackgroundWork() {
        viewModelScope.launch {
            _events.emit(UiEvent.ShowMessage("Background work started"))

            delay(2000)

            _count.update { value ->
                value + 10
            }

            _events.emit(UiEvent.ShowMessage("Background work completed"))
        }
    }

    override fun onCleared() {
        super.onCleared()

        /*
            Called when the ViewModel is permanently destroyed.
        */
        Log.d(TAG, "CounterViewModel cleared")
    }
}