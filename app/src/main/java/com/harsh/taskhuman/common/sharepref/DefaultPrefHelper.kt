package com.harsh.taskhuman.common.sharepref

import android.content.Context

/**
 * Created by Harsh Mittal on 20/06/22.
 */
class DefaultPrefHelper private constructor(
    private val preferences: Preferences
) : PrefHelper {

    override var isForegroundApp: Boolean
        get() = preferences.getBoolean(PrefNames.APP_FOREGROUND, false)
        set(value) {
            preferences.saveBoolean(PrefNames.APP_FOREGROUND, value)
        }

    override var featureConfigStateRepo: Boolean
        get() = preferences.getBoolean(PrefNames.FEATURE_CONFIG_STATE_REPO, false)
        set(value) {
            preferences.saveBoolean(PrefNames.FEATURE_CONFIG_STATE_REPO, value)
        }

    override var cookies: HashSet<String>
        get() = preferences.getStringSet(PrefNames.APP_COOKIE, hashSetOf())?.toHashSet()
            ?: hashSetOf()
        set(cookies) {
            preferences.saveStringSet(PrefNames.APP_COOKIE, cookies)
        }

    override var safetyNetToken: String
        get() = preferences.getString(PrefNames.APP_SAFETY_NET_TOKEN, "") ?: ""
        set(safetyToken) {
            preferences.saveString(PrefNames.APP_SAFETY_NET_TOKEN, safetyToken)
        }


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