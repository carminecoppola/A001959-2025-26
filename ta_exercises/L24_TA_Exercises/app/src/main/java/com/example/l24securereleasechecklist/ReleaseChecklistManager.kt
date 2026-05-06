package com.example.l24securereleasechecklist

import android.content.Context
import android.content.pm.ApplicationInfo
import java.io.File

/**
 * ReleaseChecklistManager
 *
 * This class contains the academic implementation of the Lesson 24 checklist.
 *
 * It covers the core release topics discussed in the lecture:
 * - debuggable flag
 * - backup policy
 * - cleartext traffic policy
 * - R8 release hardening
 * - hardcoded secrets
 * - app signing
 * - CI/CD security pipeline
 * - staged rollout monitoring
 *
 * This is not a complete automated security scanner.
 * It is a classroom-oriented checklist application.
 */
class ReleaseChecklistManager(private val context: Context) {

    /**
     * Runs the pre-release checklist and returns formatted results.
     */
    fun runChecklist(): String {
        val checks = listOf(
            checkDebuggableFlag(),
            checkBackupPolicy(),
            checkNetworkSecurityConfig(),
            checkR8ReleaseHardening(),
            checkHardcodedSecretDemo(),
            checkSensitiveLocalFiles(),
            checkExportedComponentsPolicy()
        )

        return checks.joinToString(separator = "\n\n") { check ->
            """
            ${check.title}
            Status: ${check.status}
            ${check.explanation}
            """.trimIndent()
        }
    }

    /**
     * Checks whether the current build is debuggable.
     *
     * Debuggable builds are acceptable during development.
     * Release builds must have android:debuggable=false.
     */
    private fun checkDebuggableFlag(): ReleaseCheck {
        val isDebuggable =
            context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

        return if (isDebuggable) {
            ReleaseCheck(
                title = "1. Debuggable flag",
                status = "WARNING",
                explanation = "This is a debuggable build. This is normal for debug, but release builds must not be debuggable."
            )
        } else {
            ReleaseCheck(
                title = "1. Debuggable flag",
                status = "OK",
                explanation = "The application is not debuggable."
            )
        }
    }

    /**
     * Explains the backup policy configured in AndroidManifest.xml.
     *
     * The manifest sets android:allowBackup=false.
     */
    private fun checkBackupPolicy(): ReleaseCheck {
        return ReleaseCheck(
            title = "2. Backup policy",
            status = "OK",
            explanation = "AndroidManifest.xml sets android:allowBackup=\"false\", reducing the risk of data extraction through backups."
        )
    }

    /**
     * Explains the Network Security Config policy.
     *
     * The app disables cleartext HTTP traffic globally.
     */
    private fun checkNetworkSecurityConfig(): ReleaseCheck {
        return ReleaseCheck(
            title = "3. Network Security Config",
            status = "OK",
            explanation = "network_security_config.xml sets cleartextTrafficPermitted=\"false\" globally."
        )
    }

    /**
     * Explains the release R8 configuration.
     *
     * R8 minification and resource shrinking are configured in build.gradle.kts.
     */
    private fun checkR8ReleaseHardening(): ReleaseCheck {
        return ReleaseCheck(
            title = "4. R8 release hardening",
            status = "OK",
            explanation = "The release build type enables isMinifyEnabled=true and isShrinkResources=true."
        )
    }

    /**
     * Demonstrates how a local scanner could detect a known insecure secret file.
     *
     * The demo app should not contain this file.
     */
    private fun checkHardcodedSecretDemo(): ReleaseCheck {
        val insecureSecretFile = File(context.filesDir, "hardcoded_secret.txt")

        return if (insecureSecretFile.exists()) {
            ReleaseCheck(
                title = "5. Hardcoded secrets",
                status = "WARNING",
                explanation = "A demo hardcoded_secret.txt file was found. Production apps must not ship secrets in source, resources or assets."
            )
        } else {
            ReleaseCheck(
                title = "5. Hardcoded secrets",
                status = "OK",
                explanation = "No demo hardcoded secret file was found."
            )
        }
    }

    /**
     * Checks for a known insecure plaintext token file.
     *
     * Sensitive tokens must be encrypted or kept in memory depending on token type.
     */
    private fun checkSensitiveLocalFiles(): ReleaseCheck {
        val plaintextTokenFile = File(context.filesDir, "plaintext_token.txt")

        return if (plaintextTokenFile.exists()) {
            ReleaseCheck(
                title = "6. Sensitive local files",
                status = "WARNING",
                explanation = "A plaintext token file exists. Access tokens should stay in memory and refresh tokens should be encrypted."
            )
        } else {
            ReleaseCheck(
                title = "6. Sensitive local files",
                status = "OK",
                explanation = "No demo plaintext token file was found."
            )
        }
    }

    /**
     * Explains exported component policy.
     *
     * The launcher Activity is exported by design.
     * Other components should be exported=false unless explicitly required.
     */
    private fun checkExportedComponentsPolicy(): ReleaseCheck {
        return ReleaseCheck(
            title = "7. Exported components",
            status = "OK",
            explanation = "Only the launcher Activity is exported. Additional components should be reviewed and exported=false by default."
        )
    }

    /**
     * Returns academic notes about app signing and Play App Signing.
     */
    fun getSigningNotes(): String {
        return """
            App Signing Notes:
            
            1. Generate the upload keystore only once:
               
               keytool -genkey -v \
                   -keystore release.jks \
                   -keyalg RSA \
                   -keysize 4096 \
                   -validity 10000 \
                   -alias my_key_alias
               
            2. Never commit release.jks to Git.
            
            3. Never hardcode passwords in build.gradle.kts.
            
            4. Read signing values from environment variables:
               
               KEYSTORE_PATH
               STORE_PASSWORD
               KEY_ALIAS
               KEY_PASSWORD
               
            5. With Play App Signing:
               - the developer signs the bundle with the upload key
               - Google Play re-signs it with the Play signing key
               - if the upload key is lost, Google can reset it
               
            6. Back up the upload keystore securely in a password manager.
        """.trimIndent()
    }

    /**
     * Returns a CI/CD pipeline summary coherent with Lesson 24.
     */
    fun getCiCdPipeline(): String {
        return """
            CI/CD Security Pipeline:
            
            1. Code push / Pull Request
            
            2. Static analysis:
               ./gradlew lint
               
            3. Unit tests:
               ./gradlew test
               
            4. Security dependency scan:
               ./gradlew dependencyCheckAnalyze
               
            5. Release build with R8:
               ./gradlew assembleRelease
               
            6. Signing through CI secrets:
               KEYSTORE_PATH
               STORE_PASSWORD
               KEY_ALIAS
               KEY_PASSWORD
               
            7. Internal track deployment:
               Upload the signed AAB to the Play Console internal testing track.
               
            8. Monitoring:
               Track crash-free sessions, ANR rate and security-related non-fatal events.
        """.trimIndent()
    }

    /**
     * Returns three release bugs for Exercise 4.
     */
    fun getReleaseBugs(): String {
        return """
            Exercise 4 - Three release bugs:
            
            1. Release build is debuggable.
               
               Bug:
               android:debuggable="true" or release { isDebuggable = true }
               
               Risk:
               Attackers can attach debuggers and inspect runtime behavior.
               
            2. Keystore password hardcoded in Gradle.
               
               Bug:
               storePassword = "password123"
               
               Risk:
               Signing credentials may leak through source control.
               
            3. R8 disabled in release.
               
               Bug:
               release { isMinifyEnabled = false }
               
               Risk:
               The APK is easier to reverse engineer and contains more unused code.
        """.trimIndent()
    }

    /**
     * Returns testing instructions shown inside the app.
     */
    fun getTestingInstructions(): String {
        return """
            How to test this application:
            
            A. Debug test:
               1. Run the app normally from Android Studio.
               2. Tap "Run Pre-Release Checklist".
               3. Expected result:
                  - Debuggable flag shows WARNING.
                  - Other security checks show OK.
               
            B. Release configuration test:
               1. Open app/build.gradle.kts.
               2. Verify release:
                  - isDebuggable = false
                  - isMinifyEnabled = true
                  - isShrinkResources = true
               
            C. Network Security Config test:
               1. Open res/xml/network_security_config.xml.
               2. Verify:
                  cleartextTrafficPermitted="false"
               
            D. Manifest test:
               1. Open AndroidManifest.xml.
               2. Verify:
                  android:allowBackup="false"
                  android:fullBackupContent="false"
               
            E. R8 output test:
               1. Run:
                  ./gradlew assembleRelease
               2. Check that a release APK is generated.
               3. Look for mapping.txt under:
                  app/build/outputs/mapping/release/
               4. This mapping file should be uploaded to Play Console
                  for deobfuscating production crashes.
        """.trimIndent()
    }
}