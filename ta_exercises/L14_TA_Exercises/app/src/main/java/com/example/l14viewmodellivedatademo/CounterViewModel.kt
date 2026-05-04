package com.example.l14viewmodellivedatademo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * CounterViewModel represents the ViewModel layer in the MVVM architecture.
 *
 * Responsibilities:
 * - hold UI-related data;
 * - expose observable LiveData to the View;
 * - survive configuration changes such as screen rotation;
 * - contain presentation logic that should not live inside the Activity.
 *
 * Important architectural rule:
 * A ViewModel must not hold references to Context, Activity or Fragment,
 * because that could cause memory leaks after configuration changes.
 */
class CounterViewModel : ViewModel() {

    companion object {
        private const val TAG = "L14_VIEWMODEL"
    }

    /**
     * MutableLiveData is private because only the ViewModel should modify state.
     * This is called the backing property pattern.
     */
    private val _counter = MutableLiveData(0)

    /**
     * LiveData is public and read-only from the Activity point of view.
     * The View observes this value and updates the UI reactively.
     */
    val counter: LiveData<Int> = _counter

    /**
     * Error message exposed to the View.
     * Null means that no error is currently shown.
     */
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        /*
            This log is useful for classroom demonstration.
            If the device is rotated, the Activity is recreated but this init block
            should not run again while the ViewModel is still alive.
        */
        Log.d(TAG, "CounterViewModel created")
    }

    /**
     * Increments the counter and clears any previous error.
     */
    fun increment() {
        val currentValue = _counter.value ?: 0
        _counter.value = currentValue + 1
        _errorMessage.value = null
    }

    /**
     * Decrements the counter.
     *
     * Error handling:
     * The counter is not allowed to become negative.
     */
    fun decrement() {
        val currentValue = _counter.value ?: 0

        if (currentValue > 0) {
            _counter.value = currentValue - 1
            _errorMessage.value = null
        } else {
            _errorMessage.value = "The counter cannot be less than zero."
        }
    }

    /**
     * Resets the counter to zero and clears any previous error.
     */
    fun reset() {
        _counter.value = 0
        _errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()

        /*
            onCleared is called when the ViewModel is permanently destroyed,
            for example when the Activity is finished with the Back button.
        */
        Log.d(TAG, "CounterViewModel cleared")
    }
}