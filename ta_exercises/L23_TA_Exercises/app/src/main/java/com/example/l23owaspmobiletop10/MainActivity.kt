package com.example.l23owaspmobiletop10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l23owaspmobiletop10.databinding.ActivityMainBinding

/**
 * MainActivity
 *
 * Entry point of the Lesson 23 demonstration application.
 *
 * This Activity presents the OWASP Mobile Top 10 categories and runs a small
 * local checklist inspired by the vulnerabilities discussed in the lecture.
 *
 * The project is intentionally simple and academic:
 * - no Compose
 * - no network dependency
 * - no dangerous operations
 * - clear mapping between buttons and OWASP concepts
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding instance used to access XML views safely.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * SecurityChecklist contains the OWASP data and local demo checks.
     */
    private lateinit var securityChecklist: SecurityChecklist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inflate the XML layout through ViewBinding.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Initialize the checklist helper.
         */
        securityChecklist = SecurityChecklist(this)

        /**
         * Shows the OWASP Mobile Top 10 overview.
         */
        binding.buttonShowTop10.setOnClickListener {
            showOwaspTop10()
        }

        /**
         * Runs local security checks.
         */
        binding.buttonRunChecklist.setOnClickListener {
            binding.textViewOutput.text = securityChecklist.runLocalChecklist()
        }

        /**
         * Shows the three buggy patterns required by Exercise 4.
         */
        binding.buttonShowBugs.setOnClickListener {
            binding.textViewOutput.text = securityChecklist.getBuggyPatterns()
        }

        /**
         * Shows correct mitigations for the buggy patterns.
         */
        binding.buttonShowMitigations.setOnClickListener {
            binding.textViewOutput.text = securityChecklist.getCorrectMitigations()
        }
    }

    /**
     * Formats and displays the OWASP Mobile Top 10 list.
     *
     * Each item includes:
     * - category identifier
     * - vulnerability name
     * - core risk
     * - recommended mitigation
     */
    private fun showOwaspTop10() {
        val items = securityChecklist.getOwaspTop10()

        val output = items.joinToString(separator = "\n\n") { item ->
            """
            ${item.id} - ${item.title}
            Risk: ${item.risk}
            Mitigation: ${item.mitigation}
            """.trimIndent()
        }

        binding.textViewOutput.text = output
    }
}