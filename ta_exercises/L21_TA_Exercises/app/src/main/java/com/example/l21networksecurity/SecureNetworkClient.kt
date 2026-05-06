package com.example.l21networksecurity

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.net.ssl.SSLPeerUnverifiedException

/**
 * SecureNetworkClient
 *
 * This class encapsulates the network logic of the application.
 * It demonstrates the main concepts of Lesson 21:
 *
 * - HTTPS communication through OkHttp
 * - blocking insecure cleartext HTTP traffic through Network Security Config
 * - certificate pinning using OkHttp CertificatePinner
 * - explicit error handling for certificate and network failures
 *
 * The purpose of this class is educational: it separates networking logic
 * from the Activity and makes the security mechanisms easier to explain.
 */
class SecureNetworkClient {

    companion object {

        /**
         * Public HTTPS test endpoint.
         *
         * This endpoint is used only to demonstrate a standard HTTPS request.
         * The TLS handshake is handled by the Android platform and OkHttp.
         */
        private const val HTTPS_URL = "https://www.google.com"

        /**
         * Plain HTTP endpoint.
         *
         * This request should fail because the application declares
         * cleartextTrafficPermitted="false" in Network Security Config.
         */
        private const val HTTP_URL = "http://example.com"

        /**
         * Hostname used for certificate pinning.
         *
         * Certificate pinning binds the application to a specific public key
         * hash for the remote server certificate chain.
         */
        private const val PINNED_HOST = "www.google.com"

        /**
         * URL used for the pinned HTTPS request.
         */
        private const val PINNED_URL = "https://www.google.com"

        /**
         * Deliberately invalid demonstration pin.
         *
         * This placeholder is intentionally not a valid Google certificate pin.
         * Therefore, the pinned request is expected to fail with
         * SSLPeerUnverifiedException.
         *
         * For a real production application, this value must be replaced with
         * the SHA-256 hash of the server certificate public key.
         */
        private const val DEMO_INVALID_PIN =
            "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA="
    }

    /**
     * Standard OkHttp client.
     *
     * This client relies on normal platform TLS validation:
     * - server certificate chain validation
     * - hostname verification
     * - trusted system Certificate Authorities
     */
    private val standardClient = OkHttpClient.Builder()
        .build()

    /**
     * OkHttp client configured with CertificatePinner.
     *
     * If the server certificate chain does not match the configured pin,
     * OkHttp throws SSLPeerUnverifiedException.
     */
    private val pinnedClient = OkHttpClient.Builder()
        .certificatePinner(
            CertificatePinner.Builder()
                .add(PINNED_HOST, DEMO_INVALID_PIN)
                .build()
        )
        .build()

    /**
     * Executes a normal HTTPS request.
     *
     * This demonstrates secure communication over TLS.
     */
    fun runHttpsRequest(): String {
        return executeRequest(
            client = standardClient,
            url = HTTPS_URL
        )
    }

    /**
     * Executes an HTTPS request with certificate pinning enabled.
     *
     * In this educational version, the configured pin is intentionally invalid,
     * so the expected result is a pinning failure.
     */
    fun runPinnedHttpsRequest(): String {
        return try {
            executeRequest(
                client = pinnedClient,
                url = PINNED_URL
            )
        } catch (e: SSLPeerUnverifiedException) {
            "Certificate pinning error: the server certificate does not match the expected pin."
        } catch (e: IOException) {
            "Network error during pinned request: ${e.message}"
        }
    }

    /**
     * Attempts to execute a plain HTTP request.
     *
     * Since cleartext traffic is disabled in network_security_config.xml,
     * Android should block this request.
     */
    fun runHttpRequest(): String {
        return try {
            executeRequest(
                client = standardClient,
                url = HTTP_URL
            )
        } catch (e: IOException) {
            "Cleartext HTTP blocked or network error: ${e.message}"
        }
    }

    /**
     * Shared request execution logic.
     *
     * This method builds an HTTP request, executes it synchronously and returns
     * a short textual result suitable for classroom demonstration.
     *
     * Network calls must not run on the Android main thread, so this method
     * is called from a background Thread in MainActivity.
     */
    private fun executeRequest(
        client: OkHttpClient,
        url: String
    ): String {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val protocol = response.protocol
            val code = response.code
            val message = response.message

            return "Request completed.\nProtocol: $protocol\nStatus: $code $message"
        }
    }
}