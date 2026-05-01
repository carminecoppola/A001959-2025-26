package com.example.lifecyclecounterapp

import androidx.lifecycle.ViewModel

/**
 * ViewModel responsible for storing the counter value.
 *
 * Academic note:
 * A ViewModel survives configuration changes such as screen rotation.
 * Therefore, it is preferable to store UI-related data here instead of
 * keeping it only inside the Activity.
 */
class CounterViewModel : ViewModel() {

    var counter: Int = 0
        private set

    fun increment() {
        counter++
    }

    fun decrement() {
        if (counter > 0) {
            counter--
        }
    }

    fun reset() {
        counter = 0
    }
}