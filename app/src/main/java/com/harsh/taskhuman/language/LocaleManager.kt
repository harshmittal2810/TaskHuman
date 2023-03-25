package com.harsh.taskhuman.language

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.annotation.StringDef
import com.harsh.taskhuman.MainApplication
import com.harsh.taskhuman.common.sharepref.PrefHelper
import java.util.*


/**
 * Created by Harsh Mittal on 20/06/22.
 */
object LocaleManager {

    private const val LANGUAGE_PREF = "language_pref"
    private const val LANGUAGE_KEY = "language_key"
    const val English = "en"
    const val Deutsch = "de"
    const val Español = "es"
    const val français = "fr"
    const val Polskie = "pl"
    const val Arabic = "ar"
    const val Vietnamese = "vi"
    const val Indonesian_Bahasa = "idn"
    const val Portuguese = "pt"
    const val Simplified_Chinese = "zh"
    const val Italian = "it"
    const val Dutch = "nl"
    const val Malaysian = "ms"
    const val Thai = "th"
    const val COUNTRY_INDIA = "IN"

    private const val LANGUAGE_PREF_FROM_LOGIN = "language_pref_login"
    private const val LANGUAGE_KEY_FROM_LOGIN = "language_key_login"
    val languageArray = arrayOf(
        English,
        Deutsch,
        Español,
        français,
        Polskie,
        Arabic,
        Vietnamese,
        Indonesian_Bahasa,
        Portuguese,
        Simplified_Chinese,
        Italian,
        Dutch,
        Malaysian,
        Thai
    )
    var commonLanguageList = ArrayList<String>()

    var backendSupportedLangaugeList = ArrayList<String>()

    @JvmStatic
    fun getSupportedLanguages(): Array<String> {
        return arrayOf(
            English,
            Deutsch,
            Español,
            français,
            Polskie,
            Arabic,
            Vietnamese,
            Indonesian_Bahasa,
            Portuguese,
            Simplified_Chinese,
            Italian,
            Dutch,
            Malaysian,
            Thai
        )
    }

    @JvmStatic
    fun getSupportedLanguagesList(): ArrayList<String> {
        return arrayListOf(
            English,
            Deutsch,
            Español,
            français,
            Polskie,
            Arabic,
            Vietnamese,
            Indonesian_Bahasa,
            Portuguese,
            Simplified_Chinese,
            Italian,
            Dutch,
            Malaysian,
            Thai
        )
    }

    /**
     * set current pref locale
     */
    @JvmStatic
    fun setLocale(mContext: Context): Context {
        return updateResources(mContext, getLanguagePref(mContext))
    }

    /**
     * Set new Locale with context
     */
    @JvmStatic
    fun setNewLocale(mContext: Context, @LocaleDef language: String): Context {
        setLanguagePref(mContext, language)
        return updateResources(mContext, language)
    }

    @JvmStatic
    fun setBackendLanguage(list: ArrayList<String>): ArrayList<String> {
        backendSupportedLangaugeList = list
        return fetchCommonLanguage(getSupportedLanguagesList(), backendSupportedLangaugeList)
    }

    @JvmStatic
    fun getBackendLanguage() = backendSupportedLangaugeList

    @JvmStatic
    fun getCommonLanguage(): ArrayList<String> {
        return if (commonLanguageList.isEmpty()) getSupportedLanguagesList()
        else {
            commonLanguageList
        }
    }


    @JvmStatic
    fun fetchCommonLanguage(
        listOne: ArrayList<String>, listTwo: ArrayList<String>
    ): ArrayList<String> {
        val result = ArrayList<String>()
        listOne.forEach { key ->
            if (listTwo.contains(key)) {
                result.add(key)
            }
        }
        commonLanguageList = result
        return result
    }

    /**
     * Get saved Locale from SharedPreferences
     *
     * @param mContext current context
     * @return current locale key by default return english locale
     */
    @JvmStatic
    fun getLanguagePref(mContext: Context): String {
        val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
            LANGUAGE_PREF, Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(LANGUAGE_KEY, English) ?: English
    }

    /**
     * set pref key
     */
    private fun setLanguagePref(mContext: Context, localeKey: String) {
        val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
            LANGUAGE_PREF, Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(LANGUAGE_KEY, localeKey)
        editor.commit()
    }

    @JvmStatic
    fun getLanguagePrefFromLogin(mContext: Context): String {
        val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
            LANGUAGE_PREF, Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(LANGUAGE_KEY_FROM_LOGIN, English) ?: English
    }

    @JvmStatic
    fun setLanguagePrefFromLogin(mContext: Context, localeKey: String) {
        val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
            LANGUAGE_PREF, Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(LANGUAGE_KEY_FROM_LOGIN, localeKey)
        editor.commit()
    }


    /**
     * update resource
     */
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        return context.createConfigurationContext(config)
    }

    /**
     * get current locale
     */
    @JvmStatic
    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) config.locales[0] else config.locale
    }

    /**
     * get language query parameter
     */
    @JvmStatic
    fun getLanguageQueryParam(context: Context): String {
        return "?lan=" + getLanguagePref(context)
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
        English,
        Español,
        Deutsch,
        français,
        Polskie,
        Arabic,
        Vietnamese,
        Indonesian_Bahasa,
        Portuguese,
        Simplified_Chinese,
        Italian,
        Dutch,
        Malaysian,
        Thai
    )
    annotation class LocaleDef {
        companion object {
            var SUPPORTED_LOCALES = languageArray
        }
    }

    fun setDeviceLanguage(pre: PrefHelper, context: Activity) {
        val deviceLanguage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources.getSystem().configuration.locales[0].language
        } else {
            Resources.getSystem().configuration.locale.language
        }
        /*
         Condition check for first time user install app or update app , So we are applying device language
         */
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            LANGUAGE_PREF, Context.MODE_PRIVATE
        )
        if (sharedPreferences.getString(LANGUAGE_KEY, "").isNullOrEmpty()) {

        } else if (sharedPreferences.getString(LANGUAGE_KEY, "").isNullOrEmpty()
                .not() and (deviceLanguage != sharedPreferences.getString(LANGUAGE_KEY, ""))
        ) {
            sharedPreferences.getString(LANGUAGE_KEY, "")?.let { setSystemLanguage(it, context) }
        }
    }

    fun setSystemLanguage(deviceLanguage: String, context: Activity, openHomePage: Int = 0) {
        when (deviceLanguage) {
            Español, français, Polskie, Deutsch, Arabic, Vietnamese, Indonesian_Bahasa, Portuguese, Simplified_Chinese, Italian, Dutch, Malaysian, Thai -> {
                setNewLocale(MainApplication.instance(), deviceLanguage)
            }
            else -> {
                setNewLocale(context, English)
            }
        }
    }


    @JvmStatic
    fun getCurrentLocale(): Locale {
        return when (getLanguagePref(MainApplication.instance())) {
            English -> {
                Locale(getLanguagePref(MainApplication.instance()), "")
            }
            Español -> {
                Locale(getLanguagePref(MainApplication.instance()), Español)
            }
            Deutsch -> {
                Locale(getLanguagePref(MainApplication.instance()), Deutsch)
            }
            français -> {
                Locale(getLanguagePref(MainApplication.instance()), français)
            }
            Polskie -> {
                Locale(getLanguagePref(MainApplication.instance()), Polskie)
            }
            Arabic -> {
                Locale(getLanguagePref(MainApplication.instance()), Arabic)
            }
            Portuguese -> {
                Locale(getLanguagePref(MainApplication.instance()), Portuguese)
            }
            Simplified_Chinese -> {
                Locale(getLanguagePref(MainApplication.instance()), Simplified_Chinese)
            }
            Indonesian_Bahasa -> {
                Locale(getLanguagePref(MainApplication.instance()), Indonesian_Bahasa)
            }
            Vietnamese -> {
                Locale(getLanguagePref(MainApplication.instance()), Vietnamese)
            }
            Italian -> {
                Locale(getLanguagePref(MainApplication.instance()), Italian)
            }
            Dutch -> {
                Locale(getLanguagePref(MainApplication.instance()), Dutch)
            }

            Malaysian -> {
                Locale(getLanguagePref(MainApplication.instance()), Malaysian)
            }

            Thai -> {
                Locale(getLanguagePref(MainApplication.instance()), Thai)
            }

            else -> {
                Locale(English, "")
            }
        }
    }

    @JvmStatic
    fun getLocaleStringResource(
        requestedLocale: Locale, resourceId: Int, context: Context
    ): String {
        val result: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // use latest api
            val config = Configuration(context.resources.configuration)
            config.setLocale(requestedLocale)
            result = context.createConfigurationContext(config).getText(resourceId).toString()
        } else {
            val resources: Resources = context.resources
            val conf: Configuration = resources.configuration
            val savedLocale: Locale = conf.locale
            conf.locale = requestedLocale
            resources.updateConfiguration(conf, null)

            result = resources.getString(resourceId)

            conf.locale = savedLocale
            resources.updateConfiguration(conf, null)
        }
        return result
    }
}