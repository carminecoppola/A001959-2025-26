package com.example.l17retrofitdemo

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * GitHubApiService defines the REST endpoints used by Retrofit.
 *
 * Retrofit reads these annotations and generates the implementation at runtime.
 */
interface GitHubApiService {

    /**
     * GET request with a path parameter.
     *
     * Example:
     * /users/octocat
     */
    @GET("users/{username}")
    suspend fun getUserByUsername(
        @Path("username") username: String
    ): GitHubUser
}