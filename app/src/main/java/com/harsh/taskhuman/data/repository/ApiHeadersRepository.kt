package com.harsh.taskhuman.data.repository

/**
 * Created by Harsh Mittal on 11/07/22.
 */
interface ApiHeadersRepository {
    fun getContentType(): String

    fun getUserAgent(): String

    fun getVersion(): String

    fun getLanguage(): String

    fun getPlatformCookie(): String

    fun getCookie(): Set<String>

    fun getSafetyNetToken(): String
}