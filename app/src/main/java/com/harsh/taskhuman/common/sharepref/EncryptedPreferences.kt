package com.harsh.taskhuman.common.sharepref

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.harsh.taskhuman.BuildConfig

/**
 * [EncryptedSharedPreferences]'s implementation for [Preferences] interface.
 * Source: [link](https://developer.android.com/topic/security/data)
 *
 * Created by Harsh Mittal on 20/06/22.
 **/
class EncryptedPreferences(
    applicationContext: Context
) : Preferences {

    private companion object {
        private const val PREFERENCES_NAME = "xoxoday_preferences"
    }

    private val preferences: SharedPreferences by lazy {
        if (BuildConfig.DEBUG) {
            applicationContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        } else {
            EncryptedSharedPreferences.create(
                applicationContext,
                PREFERENCES_NAME,
                MasterKey.Builder(applicationContext)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    override fun getBoolean(key: String, fallback: Boolean): Boolean {
        return preferences.getBoolean(key, fallback)
    }

    override fun getInt(key: String, fallback: Int): Int {
        return preferences.getInt(key, fallback)
    }

    override fun getLong(key: String, fallback: Long): Long {
        return preferences.getLong(key, fallback)
    }

    override fun getString(key: String, fallback: String?): String? {
        return preferences.getString(key, fallback)
    }

    override fun getStringSet(key: String, fallback: Set<String>?): Set<String>? {
        return preferences.getStringSet(key, fallback)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).commit()
    }

    override fun saveInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).commit()
    }

    override fun saveLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).commit()
    }

    override fun saveString(key: String, value: String?) {
        preferences.edit().putString(key, value).commit()
    }

    override fun saveStringSet(key: String, values: Set<String>?) {
        preferences.edit().putStringSet(key, values).commit()
    }

    override fun delete(key: String) {
        preferences.edit().remove(key).commit()
    }

    override fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    override fun clear() {
        preferences.edit().clear().commit()
    }

    override fun clearAllExcept(vararg keys: String) {
        preferences.all
            .keys
            .filter { key -> !keys.contains(key) }
            .forEach { key ->
                delete(key)
            }
    }
}