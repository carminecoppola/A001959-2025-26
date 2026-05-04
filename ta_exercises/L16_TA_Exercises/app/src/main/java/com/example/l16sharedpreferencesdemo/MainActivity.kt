package com.example.l16sharedpreferencesdemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.content.edit
import com.example.l16sharedpreferencesdemo.databinding.ActivityMainBinding

/**
 * MainActivity — SharedPreferences Demo.
 *
 * This Activity demonstrates the SharedPreferences API presented in Lesson 16.
 *
 * SharedPreferences is appropriate for small key-value pairs such as:
 * - simple settings;
 * - user preferences;
 * - feature flags;
 * - small primitive values.
 *
 * It is not appropriate for large structured data.
 * For structured lists use Room; for modern async key-value storage use DataStore.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * SharedPreferences is the Android key-value storage used in this demo.
     */
    private lateinit var preferences: SharedPreferences

    companion object {
        private const val TAG = "L16_SHARED_PREFS"

        /**
         * Name of the XML file where Android stores these preferences.
         * The file is private to this application because MODE_PRIVATE is used.
         */
        private const val PREFS_NAME = "app_settings"

        /**
         * Preference keys.
         * Keeping keys as constants avoids typing mistakes across the codebase.
         */
        private const val KEY_USERNAME = "username"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_LAUNCH_COUNT = "launch_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            Inflate the layout through ViewBinding.
        */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            MODE_PRIVATE means that only this app can access this preferences file.
        */
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        incrementLaunchCount()
        loadPreferences()
        setupListeners()
    }

    /**
     * Registers all UI event listeners.
     */
    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            savePreferences()
        }

        binding.btnLoad.setOnClickListener {
            loadPreferences()
        }

        binding.btnClear.setOnClickListener {
            clearPreferences()
        }
    }

    /**
     * Saves the current UI values into SharedPreferences.
     *
     * The edit { } KTX extension automatically calls apply().
     * apply() writes asynchronously and is recommended for UI code.
     */
    private fun savePreferences() {
        val username = binding.etUsername.text?.toString()?.trim()
        val darkModeEnabled = binding.switchDarkMode.isChecked

        /*
            Error handling from the TA exercise:
            avoid saving an empty username.
        */
        if (username.isNullOrEmpty()) {
            binding.etUsername.error = "Username required"
            binding.tvStatus.text = "Please enter a username before saving."
            return
        }

        preferences.edit {
            putString(KEY_USERNAME, username)
            putBoolean(KEY_DARK_MODE, darkModeEnabled)
        }

        binding.tvStatus.text = "Preferences saved."
        Log.d(TAG, "Saved username=$username, darkMode=$darkModeEnabled")
    }

    /**
     * Loads values from SharedPreferences and renders them in the UI.
     *
     * Each read operation provides a default value used when the key
     * does not exist yet.
     */
    private fun loadPreferences() {
        val username = preferences.getString(KEY_USERNAME, "")
        val darkModeEnabled = preferences.getBoolean(KEY_DARK_MODE, false)
        val launchCount = preferences.getInt(KEY_LAUNCH_COUNT, 0)

        binding.etUsername.setText(username)
        binding.switchDarkMode.isChecked = darkModeEnabled

        binding.tvStatus.text =
            "Loaded preferences. Launch count: $launchCount"

        binding.tvSummary.text =
            "Username: ${username.ifNullOrBlank("not set")}\nDark mode: $darkModeEnabled"

        Log.d(TAG, "Loaded username=$username, darkMode=$darkModeEnabled, launchCount=$launchCount")
    }

    /**
     * Removes all saved preferences.
     *
     * This demonstrates clear(), shown in the lesson slides.
     */
    private fun clearPreferences() {
        preferences.edit {
            clear()
        }

        binding.etUsername.text?.clear()
        binding.switchDarkMode.isChecked = false
        binding.tvSummary.text = "Username: not set\nDark mode: false"
        binding.tvStatus.text = "All preferences cleared."

        Log.d(TAG, "Preferences cleared")
    }

    /**
     * Counts how many times the app has been launched.
     *
     * This demonstrates reading an Int value and writing it back.
     */
    private fun incrementLaunchCount() {
        val currentCount = preferences.getInt(KEY_LAUNCH_COUNT, 0)

        preferences.edit {
            putInt(KEY_LAUNCH_COUNT, currentCount + 1)
        }

        Log.d(TAG, "Launch count updated to ${currentCount + 1}")
    }

    /**
     * Small helper used only to make the summary text clearer.
     */
    private fun String?.ifNullOrBlank(defaultValue: String): String {
        return if (this.isNullOrBlank()) defaultValue else this
    }
}