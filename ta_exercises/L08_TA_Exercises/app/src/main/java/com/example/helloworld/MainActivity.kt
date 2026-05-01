package com.example.helloworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.databinding.ActivityMainBinding

/**
 * MainActivity is the entry point of the application.
 *
 * This example is intentionally simple and academic:
 * - it shows how to connect Kotlin code to XML views;
 * - it uses ViewBinding instead of findViewById;
 * - it introduces basic input validation and error handling.
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding object generated from activity_main.xml.
     *
     * The XML file activity_main.xml generates a class called ActivityMainBinding.
     * Each view with an id becomes a Kotlin property of this object.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * onCreate is called when the Activity is created.
     * Here we initialize the user interface and define the button behavior.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGreet.setOnClickListener {
            greetStudent()
        }
    }

    /**
     * Reads the user input, validates it, and updates the message on screen.
     */
    @SuppressLint("SetTextI18n")
    private fun greetStudent() {
        val studentName = binding.etStudentName.text.toString().trim()

        if (studentName.isEmpty()) {
            binding.tvMessage.text = "Error: name cannot be empty."
            Toast.makeText(this, "Please insert your name", Toast.LENGTH_SHORT).show()
            return
        }

        binding.tvMessage.text = "Hello, $studentName! Welcome to Android Studio."
    }
}