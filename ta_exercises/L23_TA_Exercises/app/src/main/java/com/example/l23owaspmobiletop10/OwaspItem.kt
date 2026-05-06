package com.example.l23owaspmobiletop10

/**
 * OwaspItem
 *
 * Data model representing one OWASP Mobile Top 10 category.
 *
 * Each item contains:
 * - an identifier, such as M1 or M2
 * - the vulnerability name
 * - the core risk
 * - the recommended mitigation
 *
 * This keeps the OWASP content structured and easy to display in the UI.
 */
data class OwaspItem(
    val id: String,
    val title: String,
    val risk: String,
    val mitigation: String
)