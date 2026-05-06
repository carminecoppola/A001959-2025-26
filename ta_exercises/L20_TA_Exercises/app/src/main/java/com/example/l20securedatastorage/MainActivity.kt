package com.example.l20securedatastorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l20securedatastorage.databinding.ActivityMainBinding
import javax.crypto.AEADBadTagException

/**
 * MainActivity
 *
 * This Activity represents the entry point of the application.
 * It provides a simple user interface to demonstrate secure data-at-rest storage
 * using Android Keystore and AES-256-GCM encryption.
 *
 * The Activity allows the user to:
 * 1. Insert a secret string
 * 2. Encrypt and store it securely in internal storage
 * 3. Read and decrypt the stored data
 * 4. Simulate tampering to demonstrate integrity protection (GCM authentication)
 *
 * This implementation follows best practices from Lesson 20:
 * - No hardcoded cryptographic keys
 * - Use of Android Keystore for key management
 * - Use of AES/GCM for authenticated encryption
 * - No sensitive data is logged
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding instance used to access UI components safely without findViewById.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Manager class responsible for encryption, decryption and secure storage operations.
     */
    private lateinit var manager: SecureStorageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Inflate the layout using ViewBinding.
         * This ensures type-safe access to all UI elements.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Initialize the secure storage manager.
         * It encapsulates all cryptographic operations.
         */
        manager = SecureStorageManager(this)

        /**
         * Button: Encrypt and Save
         *
         * Retrieves user input, encrypts it using AES-256-GCM,
         * and stores it securely in internal storage.
         */
        binding.buttonSave.setOnClickListener {
            try {
                manager.encryptAndSave(binding.editTextSecret.text.toString())

                // Inform the user of successful secure storage
                binding.textViewOutput.text = "Saved securely"

            } catch (e: Exception) {

                // Generic error handling (no sensitive info exposed)
                binding.textViewOutput.text = "Error: ${e.message}"
            }
        }

        /**
         * Button: Read and Decrypt
         *
         * Reads encrypted data from storage and decrypts it.
         * If the ciphertext has been modified, AES-GCM detects tampering
         * and throws AEADBadTagException.
         */
        binding.buttonRead.setOnClickListener {
            try {
                val result = manager.readAndDecrypt()

                // Display decrypted plaintext
                binding.textViewOutput.text = result

            } catch (e: AEADBadTagException) {

                /**
                 * This exception indicates integrity verification failure.
                 * It means the ciphertext or authentication tag was altered.
                 * This demonstrates the security property of AES-GCM.
                 */
                binding.textViewOutput.text = "Tampering detected!"

            } catch (e: Exception) {

                // Generic fallback error handling
                binding.textViewOutput.text = "Error: ${e.message}"
            }
        }

        /**
         * Button: Tamper File
         *
         * Intentionally modifies one byte of the encrypted file.
         * This is used for educational purposes to demonstrate that:
         * - AES-GCM provides authentication
         * - Any modification invalidates the ciphertext
         */
        binding.buttonTamper.setOnClickListener {

            manager.tamper()

            binding.textViewOutput.text = "File modified"
        }
    }
}
