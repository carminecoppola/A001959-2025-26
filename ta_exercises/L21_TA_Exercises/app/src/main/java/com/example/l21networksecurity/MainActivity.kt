package com.example.l21networksecurity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l21networksecurity.databinding.ActivityMainBinding

/**
 * MainActivity
 *
 * Entry point of the Lesson 21 demonstration application.
 *
 * This Activity provides a simple UI to execute:
 * - a standard HTTPS request
 * - an HTTPS request protected with certificate pinning
 * - a plain HTTP request that should be blocked by Network Security Config
 *
 * The Activity intentionally keeps the UI simple in order to focus on the
 * network security concepts presented in the lecture slides.
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding instance used to access the XML layout safely.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Client object responsible for all network security demonstrations.
     */
    private lateinit var secureNetworkClient: SecureNetworkClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inflate the XML layout using ViewBinding.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Initialize the secure network client.
         */
        secureNetworkClient = SecureNetworkClient()

        /**
         * Demonstrates a normal HTTPS request.
         *
         * This request should succeed if the device has Internet access.
         */
        binding.buttonHttpsRequest.setOnClickListener {
            runNetworkDemo {
                secureNetworkClient.runHttpsRequest()
            }
        }

        /**
         * Demonstrates certificate pinning.
         *
         * In this project, the configured pin is intentionally invalid.
         * Therefore, this request should fail with a certificate pinning error.
         */
        binding.buttonPinnedRequest.setOnClickListener {
            runNetworkDemo {
                secureNetworkClient.runPinnedHttpsRequest()
            }
        }

        /**
         * Demonstrates cleartext traffic blocking.
         *
         * Since the Network Security Config disables HTTP globally,
         * this request should be blocked by Android.
         */
        binding.buttonHttpRequest.setOnClickListener {
            runNetworkDemo {
                secureNetworkClient.runHttpRequest()
            }
        }
    }

    /**
     * Executes a network operation on a background thread.
     *
     * Android does not allow network operations on the main UI thread.
     * This helper method keeps the UI responsive and then posts the result
     * back to the main thread.
     */
    private fun runNetworkDemo(operation: () -> String) {
        binding.textViewOutput.text = "Running network request..."

        Thread {
            val result = try {
                operation()
            } catch (e: Exception) {
                "Unexpected error: ${e.message}"
            }

            runOnUiThread {
                binding.textViewOutput.text = result
            }
        }.start()
    }
}