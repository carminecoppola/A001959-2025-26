package com.example.l24securereleasechecklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l24securereleasechecklist.databinding.ActivityMainBinding

/**
 * MainActivity
 *
 * Entry point of the Lesson 24 demonstration application.
 *
 * This Activity provides buttons to demonstrate:
 * - the secure release checklist
 * - app signing notes
 * - CI/CD security pipeline
 * - common release bugs
 * - testing instructions
 *
 * The UI is intentionally simple because the purpose is academic:
 * students should focus on release hardening concepts, not UI complexity.
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding instance used to access XML views safely.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Manager responsible for generating checklist and release notes.
     */
    private lateinit var checklistManager: ReleaseChecklistManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inflate XML layout using ViewBinding.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Initialize the release checklist manager.
         */
        checklistManager = ReleaseChecklistManager(this)

        /**
         * Runs the pre-release security checklist.
         */
        binding.buttonRunChecklist.setOnClickListener {
            binding.textViewOutput.text = checklistManager.runChecklist()
        }

        /**
         * Shows app signing and Play App Signing notes.
         */
        binding.buttonShowSigning.setOnClickListener {
            binding.textViewOutput.text = checklistManager.getSigningNotes()
        }

        /**
         * Shows the CI/CD pipeline described in Lesson 24.
         */
        binding.buttonShowCiCd.setOnClickListener {
            binding.textViewOutput.text = checklistManager.getCiCdPipeline()
        }

        /**
         * Shows the three buggy release patterns for Exercise 4.
         */
        binding.buttonShowBugs.setOnClickListener {
            binding.textViewOutput.text = checklistManager.getReleaseBugs()
        }

        /**
         * Shows practical testing instructions.
         */
        binding.buttonShowTesting.setOnClickListener {
            binding.textViewOutput.text = checklistManager.getTestingInstructions()
        }
    }
}