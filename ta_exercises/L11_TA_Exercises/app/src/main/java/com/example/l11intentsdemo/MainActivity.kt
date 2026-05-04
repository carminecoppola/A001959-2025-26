package com.example.l11intentsdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.l11intentsdemo.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "L11_MAIN"
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            val username = binding.etUsername.text?.toString()?.trim()

            if (username.isNullOrEmpty()) {
                binding.etUsername.error = "Required"
                return@setOnClickListener
            }

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)

            Log.d(TAG, "Sending username=$username")

            startActivity(intent)
        }
    }
}