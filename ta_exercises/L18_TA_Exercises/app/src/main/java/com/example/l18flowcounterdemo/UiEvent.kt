package com.example.l18flowcounterdemo

/**
 * UiEvent represents one-shot UI events.
 *
 * In the lesson, SharedFlow is used for events such as:
 * - showing a Toast;
 * - showing a Snackbar;
 * - navigation;
 * - displaying an error message once.
 *
 * Unlike StateFlow, SharedFlow does not represent persistent screen state.
 */
sealed class UiEvent {

    /**
     * Event used to show a short message to the user.
     */
    data class ShowMessage(val message: String) : UiEvent()
}