package com.example.l17retrofitdemo

import com.google.gson.annotations.SerializedName

/**
 * GitHubUser represents the JSON response returned by:
 *
 * GET https://api.github.com/users/{username}
 *
 * Gson maps JSON fields to Kotlin properties.
 * @SerializedName is used when the JSON name differs from the Kotlin property name.
 */
data class GitHubUser(
    val login: String,
    val name: String?,
    val bio: String?,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("html_url")
    val profileUrl: String
)