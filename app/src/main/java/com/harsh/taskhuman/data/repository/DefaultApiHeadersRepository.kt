package com.harsh.taskhuman.data.repository

import android.content.Context
import com.harsh.taskhuman.BuildConfig
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.language.LocaleManager

/**
 * Created by Harsh Mittal on 17/07/22.
 *
 **/
class DefaultApiHeadersRepository(
    private val applicationContext: Context, private val prefHelper: PrefHelper
) : ApiHeadersRepository {

    override fun getContentType(): String = "application/json"

    override fun getUserAgent() = "android"

    override fun getVersion(): String = BuildConfig.VERSION_NAME

    override fun getLanguage(): String {
        return LocaleManager.getLanguagePref(applicationContext)
    }

    override fun getPlatformCookie(): String = "2"

    override fun getCookie(): Set<String> = prefHelper.cookies

    override fun getSafetyNetToken(): String = prefHelper.safetyNetToken
}