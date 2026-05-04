package com.example.l17retrofitdemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitInstance creates and exposes a singleton Retrofit API service.
 *
 * This follows the setup shown in the lesson:
 * - base URL;
 * - OkHttpClient;
 * - HttpLoggingInterceptor;
 * - GsonConverterFactory.
 */
object RetrofitInstance {

    private const val BASE_URL = "https://api.github.com/"

    /**
     * Logs HTTP request and response bodies in Logcat.
     * This is useful for classroom debugging and demonstration.
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * OkHttpClient is the underlying HTTP client used by Retrofit.
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Lazily creates the API service only when first used.
     */
    val api: GitHubApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }
}