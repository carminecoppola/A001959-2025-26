package com.example.l20securedatastorage

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.File
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * SecureStorageManager
 *
 * This class encapsulates all secure storage operations.
 * It is responsible for:
 * - generating or retrieving an AES key from Android Keystore
 * - encrypting plaintext using AES-256-GCM
 * - storing encrypted data in internal storage
 * - reading and decrypting stored data
 * - simulating file tampering for educational purposes
 *
 * This class follows the secure data storage principles discussed in Lesson 20:
 * - cryptographic keys are not hardcoded
 * - the Android Keystore is used for key management
 * - AES/GCM/NoPadding is used for authenticated encryption
 * - the IV is generated randomly for each encryption operation
 */
class SecureStorageManager(private val context: Context) {

    companion object {

        /**
         * Name of the Android Keystore provider.
         * Keys stored here are managed by the Android system and are not exposed
         * directly to the application code.
         */
        private const val KEYSTORE = "AndroidKeyStore"

        /**
         * Alias used to identify the secret key inside Android Keystore.
         * The alias is public metadata; it is not the cryptographic key itself.
         */
        private const val KEY_ALIAS = "secure_key"

        /**
         * Cryptographic transformation used by the Cipher.
         *
         * AES/GCM/NoPadding provides:
         * - confidentiality through AES encryption
         * - integrity and authenticity through GCM authentication
         */
        private const val TRANSFORMATION = "AES/GCM/NoPadding"

        /**
         * Standard IV size for AES-GCM.
         * The IV is not secret, but it must be unique for each encryption.
         */
        private const val IV_SIZE = 12
    }

    /**
     * Encrypts the provided plaintext and saves it to internal storage.
     *
     * The stored file contains:
     * [12-byte IV][ciphertext + GCM authentication tag]
     *
     * The IV is stored together with the ciphertext because it is required
     * during decryption. This is secure because the IV is not secret.
     */
    fun encryptAndSave(text: String) {
        val key = getKey()
        val cipher = Cipher.getInstance(TRANSFORMATION)

        /**
         * Initialize the Cipher in encryption mode.
         * Android automatically generates a fresh random IV for AES-GCM.
         */
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val iv = cipher.iv
        val encrypted = cipher.doFinal(text.toByteArray())

        /**
         * Store encrypted data inside the app internal storage directory:
         * /data/data/<package_name>/files/
         *
         * The file content is not plaintext.
         */
        val file = File(context.filesDir, "data.bin")
        file.writeBytes(iv + encrypted)
    }

    /**
     * Reads the encrypted file from internal storage and decrypts it.
     *
     * If the encrypted data has been modified, AES-GCM authentication fails
     * and cipher.doFinal() throws AEADBadTagException.
     */
    fun readAndDecrypt(): String {
        val file = File(context.filesDir, "data.bin")
        val bytes = file.readBytes()

        /**
         * Extract the IV and ciphertext from the stored payload.
         * The first 12 bytes are the IV.
         * The remaining bytes are ciphertext plus GCM authentication tag.
         */
        val iv = bytes.copyOfRange(0, IV_SIZE)
        val encrypted = bytes.copyOfRange(IV_SIZE, bytes.size)

        val key = getKey()
        val cipher = Cipher.getInstance(TRANSFORMATION)

        /**
         * GCMParameterSpec defines the authentication tag length and the IV.
         * 128 bits is the standard authentication tag length for AES-GCM.
         */
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)

        /**
         * During doFinal(), AES-GCM verifies the authentication tag.
         * If verification succeeds, plaintext is returned.
         * If verification fails, an exception is thrown.
         */
        return String(cipher.doFinal(encrypted))
    }

    /**
     * Intentionally modifies the encrypted file.
     *
     * This method is included only for classroom demonstration.
     * It proves that AES-GCM detects tampering: after this modification,
     * decryption should fail with AEADBadTagException.
     */
    fun tamper() {
        val file = File(context.filesDir, "data.bin")
        val bytes = file.readBytes()

        /**
         * Flip one bit in the last byte of the encrypted payload.
         * This corrupts either the ciphertext or the authentication tag.
         */
        bytes[bytes.size - 1] = (bytes.last().toInt() xor 1).toByte()

        file.writeBytes(bytes)
    }

    /**
     * Retrieves the AES key from Android Keystore.
     *
     * If the key already exists, it is reused.
     * If it does not exist, a new AES-256 key is generated inside Keystore.
     */
    private fun getKey(): SecretKey {
        val ks = KeyStore.getInstance(KEYSTORE)
        ks.load(null)

        /**
         * Try to retrieve an existing key using its alias.
         * The key material itself is not hardcoded and is not stored in app files.
         */
        val existing = ks.getKey(KEY_ALIAS, null) as? SecretKey
        if (existing != null) return existing

        /**
         * Create a KeyGenerator configured to generate AES keys
         * inside Android Keystore.
         */
        val generator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE
        )

        /**
         * Define the cryptographic properties of the key:
         * - purpose: encryption and decryption
         * - key size: 256 bits
         * - block mode: GCM
         * - padding: none, as required by GCM
         */
        val spec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setKeySize(256)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        generator.init(spec)

        /**
         * Generate and return the secret key.
         * The key is created and managed by Android Keystore.
         */
        return generator.generateKey()
    }
}