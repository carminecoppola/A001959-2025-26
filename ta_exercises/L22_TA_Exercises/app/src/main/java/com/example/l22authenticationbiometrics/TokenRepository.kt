package com.example.l22authenticationbiometrics

import android.content.Context
import android.util.Base64

/**
 * TokenRepository
 *
 * This class stores the encrypted refresh token demo payload.
 *
 * The purpose is educational:
 * - access tokens should remain in memory only
 * - refresh tokens require persistent protected storage
 * - tokens must never be logged
 *
 * In a production-grade application, EncryptedSharedPreferences or another
 * secure storage layer should be used for persistent token storage.
 */
class TokenRepository(context: Context) {

    companion object {
        private const val PREFS_NAME = "secure_token_prefs"
        private const val KEY_ENCRYPTED_TOKEN = "encrypted_refresh_token"
        private const val KEY_TOKEN_IV = "refresh_token_iv"
    }

    /**
     * Private SharedPreferences used to store encrypted bytes.
     *
     * The plaintext token is never stored here.
     */
    private val prefs = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    /**
     * Saves encrypted token bytes and IV as Base64 strings.
     *
     * The IV is not secret, but it is required for AES-GCM decryption.
     */
    fun saveEncryptedToken(
        encryptedToken: ByteArray,
        iv: ByteArray
    ) {
        prefs.edit()
            .putString(KEY_ENCRYPTED_TOKEN, Base64.encodeToString(encryptedToken, Base64.NO_WRAP))
            .putString(KEY_TOKEN_IV, Base64.encodeToString(iv, Base64.NO_WRAP))
            .apply()
    }

    /**
     * Loads encrypted token bytes.
     *
     * Returns null if no encrypted token is available.
     */
    fun loadEncryptedToken(): ByteArray? {
        val value = prefs.getString(KEY_ENCRYPTED_TOKEN, null) ?: return null
        return Base64.decode(value, Base64.NO_WRAP)
    }

    /**
     * Loads the IV used during encryption.
     *
     * Returns null if no IV is available.
     */
    fun loadIv(): ByteArray? {
        val value = prefs.getString(KEY_TOKEN_IV, null) ?: return null
        return Base64.decode(value, Base64.NO_WRAP)
    }

    /**
     * Deletes the stored encrypted token.
     *
     * This models a logout/session reset operation.
     */
    fun clear() {
        prefs.edit()
            .remove(KEY_ENCRYPTED_TOKEN)
            .remove(KEY_TOKEN_IV)
            .apply()
    }
}