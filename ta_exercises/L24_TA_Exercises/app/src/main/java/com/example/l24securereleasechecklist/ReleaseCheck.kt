package com.example.l24securereleasechecklist

/**
 * ReleaseCheck
 *
 * Data model representing one pre-release security check.
 *
 * Each check contains:
 * - a title
 * - a status
 * - an explanation
 *
 * This keeps the checklist structured and easy to display in the UI.
 */
data class ReleaseCheck(
    val title: String,
    val status: String,
    val explanation: String
)