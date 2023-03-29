package com.harsh.taskhuman.common.sharepref

import android.content.Context

/**
 * Created by Harsh Mittal on 20/06/22.
 */
class DefaultPrefHelper private constructor(
    private val preferences: Preferences
) : PrefHelper {

    override var envBaseUrl: String
        get() = preferences.getString(PrefNames.API_BASE_URL, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.API_BASE_URL, value)
        }

    override var gcmToken: String
        get() = preferences.getString(PrefNames.GCM_TOKEN_ID, "") ?: ""
        set(deviceLocale) {
            preferences.saveString(PrefNames.GCM_TOKEN_ID, deviceLocale)
        }

    override var isLogin: Boolean
        get() = preferences.getBoolean(PrefNames.LOGIN, false)
        set(isLogin) {
            preferences.saveBoolean(PrefNames.LOGIN, isLogin)
        }

    override fun clearSharedPref() {
        preferences.clearAllExcept(PrefNames.API_BASE_URL)
    }

    companion object {
        private var defaultInstance: PrefHelper? = null

        @Synchronized
        fun getInstance(applicationContext: Context): PrefHelper {
            if (defaultInstance == null) {
                defaultInstance = DefaultPrefHelper(
                    EncryptedPreferences(
                        applicationContext
                    )
                )
            }
            return defaultInstance!!
        }
    }
}