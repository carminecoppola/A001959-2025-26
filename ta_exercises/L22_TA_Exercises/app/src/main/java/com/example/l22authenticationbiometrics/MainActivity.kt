package com.example.l22authenticationbiometrics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.l22authenticationbiometrics.databinding.ActivityMainBinding
import javax.crypto.Cipher

/**
 * MainActivity
 *
 * Entry point of the Lesson 22 demonstration application.
 *
 * This Activity demonstrates:
 * - checking BIOMETRIC_STRONG availability
 * - encrypting a demo refresh token
 * - decrypting the token only after biometric authentication
 * - explaining the OAuth2 Authorization Code + PKCE flow
 *
 * The code intentionally avoids logging tokens or storing access tokens.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tokenRepository: TokenRepository
    private lateinit var biometricCryptoManager: BiometricCryptoManager

    /**
     * Demo refresh token used only for classroom purposes.
     *
     * In a real application, this value would come from the OAuth2 token endpoint
     * after an Authorization Code + PKCE exchange.
     */
    private val demoRefreshToken = "demo_refresh_token_from_oauth2_pkce_flow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenRepository = TokenRepository(this)
        biometricCryptoManager = BiometricCryptoManager()

        binding.buttonCheckBiometrics.setOnClickListener {
            checkBiometricAvailability()
        }

        binding.buttonStoreToken.setOnClickListener {
            encryptDemoRefreshToken()
        }

        binding.buttonUnlockToken.setOnClickListener {
            unlockTokenWithBiometrics()
        }

        binding.buttonShowPkce.setOnClickListener {
            showPkceSummary()
        }
    }

    /**
     * Checks whether the device can perform BIOMETRIC_STRONG authentication.
     *
     * This is coherent with Lesson 22 because only BIOMETRIC_STRONG sensors
     * can be used with CryptoObject for Keystore-backed cryptographic operations.
     */
    private fun checkBiometricAvailability() {
        val biometricManager = BiometricManager.from(this)

        val message = when (
            biometricManager.canAuthenticate(BIOMETRIC_STRONG)
        ) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                "BIOMETRIC_STRONG is available. CryptoObject can be used."

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                "No biometric hardware is available on this device."

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                "Biometric hardware is currently unavailable."

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                "No strong biometric credential is enrolled."

            else ->
                "BIOMETRIC_STRONG is not available on this device."
        }

        binding.textViewOutput.text = message
    }

    /**
     * Encrypts a demo refresh token using a biometric-protected Keystore key.
     *
     * The plaintext token is not stored. Only ciphertext and IV are persisted.
     */
    private fun encryptDemoRefreshToken() {
        try {
            val cipher = biometricCryptoManager.getEncryptionCipher()

            val encryptedToken = cipher.doFinal(
                demoRefreshToken.toByteArray(Charsets.UTF_8)
            )

            val iv = cipher.iv

            tokenRepository.saveEncryptedToken(
                encryptedToken = encryptedToken,
                iv = iv
            )

            binding.textViewOutput.text =
                "Demo refresh token encrypted and stored. Plaintext was not persisted."

        } catch (e: Exception) {
            binding.textViewOutput.text = "Encryption error: ${e.message}"
        }
    }

    /**
     * Starts biometric authentication and decrypts the token only on success.
     *
     * The decryption Cipher is wrapped in a BiometricPrompt.CryptoObject,
     * exactly as shown in the Lesson 22 biometric flow.
     */
    private fun unlockTokenWithBiometrics() {
        val encryptedToken = tokenRepository.loadEncryptedToken()
        val iv = tokenRepository.loadIv()

        if (encryptedToken == null || iv == null) {
            binding.textViewOutput.text =
                "No encrypted token found. Encrypt the demo token first."
            return
        }

        try {
            val cipher = biometricCryptoManager.getDecryptionCipher(iv)
            showBiometricPrompt(cipher, encryptedToken)
        } catch (e: Exception) {
            binding.textViewOutput.text = "Cipher initialization error: ${e.message}"
        }
    }

    /**
     * Displays BiometricPrompt and performs decryption after successful
     * biometric authentication.
     */
    private fun showBiometricPrompt(
        cipher: Cipher,
        encryptedToken: ByteArray
    ) {
        val executor = ContextCompat.getMainExecutor(this)

        val prompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)

                    try {
                        val authenticatedCipher = result.cryptoObject?.cipher

                        if (authenticatedCipher == null) {
                            binding.textViewOutput.text =
                                "Authentication succeeded, but CryptoObject is missing."
                            return
                        }

                        val decryptedBytes = authenticatedCipher.doFinal(encryptedToken)
                        val refreshToken = String(decryptedBytes, Charsets.UTF_8)

                        /**
                         * This displays the token only for classroom demonstration.
                         * In production, tokens should not be displayed or logged.
                         */
                        binding.textViewOutput.text =
                            "Biometric authentication succeeded.\nUnlocked token:\n$refreshToken"

                    } catch (e: Exception) {
                        binding.textViewOutput.text =
                            "Decryption error after authentication: ${e.message}"
                    }
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)

                    binding.textViewOutput.text =
                        "Biometric error $errorCode: $errString"
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                    binding.textViewOutput.text =
                        "Biometric attempt failed. The prompt remains active."
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authenticate")
            .setSubtitle("Use a strong biometric credential to unlock the token")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .build()

        prompt.authenticate(
            promptInfo,
            BiometricPrompt.CryptoObject(cipher)
        )
    }

    /**
     * Shows a concise academic summary of the OAuth2 Authorization Code + PKCE flow.
     *
     * This project does not contact a real OAuth2 provider because that would
     * require external tenant configuration. The logic remains coherent with
     * the lecture: AppAuth should be used for a real implementation.
     */
    private fun showPkceSummary() {
        binding.textViewOutput.text = """
            OAuth2 Authorization Code + PKCE:
            
            1. The app generates a code_verifier.
            2. The app sends code_challenge = BASE64(SHA256(code_verifier)).
            3. The user authenticates in a browser or Custom Tab.
            4. The server returns a short-lived authorization code.
            5. The app exchanges code + code_verifier for tokens.
            
            Token strategy:
            - Access token: memory only.
            - Refresh token: encrypted persistent storage.
            - ID token: decode claims, do not use for API authorization.
            - Never log tokens.
        """.trimIndent()
    }
}