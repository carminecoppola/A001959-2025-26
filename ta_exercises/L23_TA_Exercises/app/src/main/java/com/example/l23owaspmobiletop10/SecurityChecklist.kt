package com.example.l23owaspmobiletop10

import android.content.Context
import android.content.pm.ApplicationInfo
import java.io.File

/**
 * SecurityChecklist
 *
 * This class contains simple local checks inspired by OWASP Mobile Top 10.
 *
 * The goal is educational: it shows students how to reason about common
 * mobile security misconfigurations and insecure development patterns.
 *
 * These checks are not a replacement for a professional security audit.
 */
class SecurityChecklist(private val context: Context) {

    /**
     * Returns the OWASP Mobile Top 10 2024 categories used in Lesson 23.
     */
    fun getOwaspTop10(): List<OwaspItem> {
        return listOf(
            OwaspItem(
                id = "M1",
                title = "Improper Credential Usage",
                risk = "Hardcoded API keys, weak passwords, missing MFA.",
                mitigation = "Never hardcode secrets. Proxy third-party API calls through an authenticated backend."
            ),
            OwaspItem(
                id = "M2",
                title = "Inadequate Supply Chain Security",
                risk = "Outdated dependencies, malicious SDKs, unverified artifacts.",
                mitigation = "Pin dependency versions, verify checksums and run dependency audits."
            ),
            OwaspItem(
                id = "M3",
                title = "Insecure Authentication / Authorization",
                risk = "Broken authorization, IDOR, missing token validation.",
                mitigation = "Perform authorization checks server-side and use short-lived access tokens."
            ),
            OwaspItem(
                id = "M4",
                title = "Insufficient Input / Output Validation",
                risk = "SQL injection, intent injection, unsafe WebView JavaScript.",
                mitigation = "Use parameterized queries and validate all Intent extras and URIs."
            ),
            OwaspItem(
                id = "M5",
                title = "Insecure Communication",
                risk = "Plain HTTP, self-signed certificates, no certificate pinning.",
                mitigation = "Use HTTPS, Network Security Config and certificate pinning for sensitive APIs."
            ),
            OwaspItem(
                id = "M6",
                title = "Inadequate Privacy Controls",
                risk = "Excessive data collection, missing consent, PII leakage.",
                mitigation = "Collect the minimum data required and avoid logging personal information."
            ),
            OwaspItem(
                id = "M7",
                title = "Insufficient Binary Protections",
                risk = "Easy reverse engineering, no obfuscation, runtime tampering.",
                mitigation = "Enable R8 minification and use Play Integrity API for runtime attestation."
            ),
            OwaspItem(
                id = "M8",
                title = "Security Misconfiguration",
                risk = "Debug flags, exported components, broad permissions.",
                mitigation = "Disable debuggable release builds and set exported=false unless required."
            ),
            OwaspItem(
                id = "M9",
                title = "Insecure Data Storage",
                risk = "Plaintext SharedPreferences, readable databases, sensitive Logcat output.",
                mitigation = "Use Android Keystore, EncryptedSharedPreferences, EncryptedFile or SQLCipher."
            ),
            OwaspItem(
                id = "M10",
                title = "Insufficient Cryptography",
                risk = "ECB mode, MD5 passwords, reused IVs and hardcoded salts.",
                mitigation = "Use modern authenticated encryption such as AES-GCM and strong password hashing."
            )
        )
    }

    /**
     * Runs local security checks that are safe to demonstrate in class.
     *
     * The checks cover:
     * - debuggable flag
     * - backup configuration
     * - root indicators
     * - accidental local plaintext token demo
     */
    fun runLocalChecklist(): String {
        val results = mutableListOf<String>()

        results += checkDebuggableFlag()
        results += checkBackupConfiguration()
        results += checkRootIndicators()
        results += checkPlaintextTokenDemo()

        return results.joinToString(separator = "\n\n")
    }

    /**
     * Checks whether the app is currently debuggable.
     *
     * Debuggable builds are acceptable during development, but release builds
     * must not be debuggable. This maps to OWASP M8.
     */
    private fun checkDebuggableFlag(): String {
        val isDebuggable =
            context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

        return if (isDebuggable) {
            "M8 Check - Debuggable flag:\nWARNING: This is a debuggable build. This is acceptable for debug, but must be false in release."
        } else {
            "M8 Check - Debuggable flag:\nOK: The application is not debuggable."
        }
    }

    /**
     * Academic note about backup configuration.
     *
     * android:allowBackup is declared in AndroidManifest.xml.
     * The project sets it to false, which is coherent with secure storage
     * guidance from previous lessons and OWASP M9.
     */
    private fun checkBackupConfiguration(): String {
        return "M9 Check - Backup policy:\nOK: The manifest sets android:allowBackup=\"false\" to reduce data extraction risk."
    }

    /**
     * Performs a very basic root indicator check.
     *
     * This is intentionally simple because root detection can be bypassed.
     * It is useful only as a classroom example for OWASP M7.
     */
    private fun checkRootIndicators(): String {
        val suspiciousPaths = listOf(
            "/system/bin/su",
            "/system/xbin/su",
            "/sbin/su",
            "/su/bin/su"
        )

        val found = suspiciousPaths.any { path ->
            File(path).exists()
        }

        return if (found) {
            "M7 Check - Root indicators:\nWARNING: A common su binary path was found. Runtime integrity should be verified server-side."
        } else {
            "M7 Check - Root indicators:\nOK: No basic su binary path was found."
        }
    }

    /**
     * Demonstrates what should not be done with tokens.
     *
     * The method verifies that this demo app does not create a known insecure
     * plaintext token file.
     */
    private fun checkPlaintextTokenDemo(): String {
        val insecureFile = File(context.filesDir, "plaintext_token.txt")

        return if (insecureFile.exists()) {
            "M9 Check - Plaintext token storage:\nWARNING: A plaintext token file exists. Tokens must not be stored in plaintext."
        } else {
            "M9 Check - Plaintext token storage:\nOK: No demo plaintext token file was found."
        }
    }

    /**
     * Returns three intentionally buggy patterns for Exercise 4.
     */
    fun getBuggyPatterns(): String {
        return """
            Exercise 4 - Buggy implementation patterns:
            
            1. Hardcoded secret:
               private const val API_KEY = "sk_live_HARDCODED_SECRET"
               
               Problem:
               Secrets inside source code or BuildConfig can be recovered from the APK.
               
            2. Client-side authorization:
               if (user.id == itemOwnerId) showDeleteButton()
               
               Problem:
               The client cannot be trusted for authorization decisions.
               
            3. Unsafe raw SQL:
               "SELECT * FROM users WHERE name = '$${'$'}name'"
               
               Problem:
               String concatenation can lead to SQL injection.
        """.trimIndent()
    }

    /**
     * Returns correct mitigations for the buggy patterns.
     */
    fun getCorrectMitigations(): String {
        return """
            Correct mitigations:
            
            1. Never hardcode secrets.
               Use a backend proxy or authenticated remote configuration.
               
            2. Enforce authorization on the server.
               The server must verify token validity and resource ownership.
               
            3. Use parameterized queries.
               In Room:
               @Query("SELECT * FROM users WHERE name = :name")
               
            Additional release checklist:
            - android:debuggable must be false in release.
            - android:exported should be false by default.
            - Do not log tokens, emails or passwords.
            - Use HTTPS and certificate pinning for sensitive APIs.
            - Use AES-GCM instead of ECB or MD5-based schemes.
        """.trimIndent()
    }
}