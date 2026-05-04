package com.example.l11intentsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.l11intentsdemo.databinding.ActivityDetailBinding

class DetailActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(MainActivity.EXTRA_USERNAME)
            ?: "Unknown"

        binding.tvWelcome.text = "Welcome $username"
    }
}