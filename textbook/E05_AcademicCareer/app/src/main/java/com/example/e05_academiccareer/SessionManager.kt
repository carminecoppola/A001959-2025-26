package com.example.e05_academiccareer

import android.content.Context
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "unipa_session",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveCredentials(matricola: String, password: String) {
        prefs.edit()
            .putString("matricola", matricola)
            .putString("password", password)
            .apply()
    }

    fun getAuthHeader(): String? {
        val matricola = prefs.getString("matricola", null) ?: return null
        val password  = prefs.getString("password",  null) ?: return null
        val encoded = Base64.encodeToString(
            "$matricola:$password".toByteArray(Charsets.UTF_8),
            Base64.NO_WRAP
        )
        return "Basic $encoded"
    }

    fun isLoggedIn(): Boolean = getAuthHeader() != null

    fun clearSession() = prefs.edit().clear().apply()
}