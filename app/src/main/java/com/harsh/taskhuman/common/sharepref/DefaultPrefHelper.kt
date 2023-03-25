package com.harsh.taskhuman.common.sharepref

import android.content.Context

/**
 * Created by Harsh Mittal on 20/06/22.
 */
class DefaultPrefHelper private constructor(
    private val preferences: Preferences
) : PrefHelper {

//    override var deviceLocale: String
//        get() = preferences.getString(PrefNames.DEVICE_LANGUAGE_SELECTED, "") ?: ""
//        set(deviceLocale) {
//            preferences.saveString(PrefNames.DEVICE_LANGUAGE_SELECTED, deviceLocale)
//        }

    override var isForegroundApp: Boolean
        get() = preferences.getBoolean(PrefNames.APP_FOREGROUND, false)
        set(value) {
            preferences.saveBoolean(PrefNames.APP_FOREGROUND, value)
        }

    override var isAwardAllowed: Boolean
        get() = preferences.getBoolean(PrefNames.IS_AWARD_ALLOWED, false)
        set(value) {
            preferences.saveBoolean(PrefNames.IS_AWARD_ALLOWED, value)
        }

    override var isWishBoardAccess: Boolean
        get() = preferences.getBoolean(PrefNames.IS_WISHBOARD_ALLOWED, false)
        set(value) {
            preferences.saveBoolean(PrefNames.IS_WISHBOARD_ALLOWED, value)
        }
    override var viewLeaderboardAccess: Boolean
        get() = preferences.getBoolean(PrefNames.LEADERBOARD_ACCESS, false)
        set(value) {
            preferences.saveBoolean(PrefNames.LEADERBOARD_ACCESS, value)
        }
    override var createBudgetAccess: Boolean
        get() = preferences.getBoolean(PrefNames.CREATE_GROUP_ACCESS, false)
        set(value) {
            preferences.saveBoolean(PrefNames.CREATE_GROUP_ACCESS, value)
        }

    override var envBaseUrl: String
        get() = preferences.getString(PrefNames.API_BASE_URL, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.API_BASE_URL, value)
        }

    override var onBoardingPopupSkipped: Boolean
        get() = preferences.getBoolean(PrefNames.ONBOARDING_POPUP_SKIPPED, false)
        set(popUpSkipped) {
            preferences.saveBoolean(PrefNames.ONBOARDING_POPUP_SKIPPED, popUpSkipped)
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

    override var deepLinkPattern: HashSet<String>
        get() = preferences.getStringSet(PrefNames.DEEPLINK_PATTERN, hashSetOf())?.toHashSet()
            ?: hashSetOf()
        set(deeplinkPattern) {
            preferences.saveStringSet(PrefNames.DEEPLINK_PATTERN, deeplinkPattern)
        }

    override var safetyNetToken: String
        get() = preferences.getString(PrefNames.APP_SAFETY_NET_TOKEN, "")
            ?: ""
        set(safetyToken) {
            preferences.saveString(PrefNames.APP_SAFETY_NET_TOKEN, safetyToken)
        }

    override var firstName: String
        get() = preferences.getString(PrefNames.USER_FIRST_NAME, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_FIRST_NAME, value)
        }

    override var lastName: String
        get() = preferences.getString(PrefNames.USER_LAST_NAME, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_LAST_NAME, value)
        }

    override var userId: String
        get() = preferences.getString(PrefNames.USER_ID, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_ID, value)
        }

    override var designation: String
        get() = preferences.getString(PrefNames.DESIGNATION, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.DESIGNATION, value)
        }

    override var profileImage: String
        get() = preferences.getString(PrefNames.USER_PROFILE_IMAGE, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_PROFILE_IMAGE, value)
        }

    override var email: String
        get() = preferences.getString(PrefNames.USER_EMAIL, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_EMAIL, value)
        }

    override var subDomain: String
        get() = preferences.getString(PrefNames.SUB_DOMAIN, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.SUB_DOMAIN, value)
        }

    override var empulsRedemptionStatus: String
        get() = preferences.getString(PrefNames.EMPULS_REDEMPTION_STATUS, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.EMPULS_REDEMPTION_STATUS, value)
        }

    override var countryId: String
        get() = preferences.getString(PrefNames.COUNTRY_ID, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.COUNTRY_ID, value)
        }

    override var companyLogo: String
        get() = preferences.getString(PrefNames.COMPANY_LOGO, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.COMPANY_LOGO, value)
        }

    override var navigationColor: String
        get() = preferences.getString(PrefNames.NAVIGATION_COLOR, "#FFFFFF") ?: "#FFFFFF"
        set(value) {
            preferences.saveString(PrefNames.NAVIGATION_COLOR, value)
        }

    override var backgroundColor: String
        get() = preferences.getString(PrefNames.BACKGROUND_COLOR, "#EBF1F4") ?: "#EBF1F4"
        set(backgroundColor) {
            preferences.saveString(PrefNames.BACKGROUND_COLOR, backgroundColor)
        }

    override var badgeColor: String
        get() = preferences.getString(PrefNames.BADGE_COLOR, "#0070FF") ?: "#0070FF"
        set(value) {
            preferences.saveString(PrefNames.BADGE_COLOR, value)
        }

    override var textColor: String
        get() = preferences.getString(PrefNames.TEXT_COLOR, "#242424") ?: "#242424"
        set(value) {
            preferences.saveString(PrefNames.TEXT_COLOR, value)
        }

    override var hoverColor: String
        get() = preferences.getString(PrefNames.HOVER_COLOR, "#EBF1F4") ?: "#EBF1F4"
        set(value) {
            preferences.saveString(PrefNames.HOVER_COLOR, value)
        }

    override var pointsName: String
        get() = preferences.getString(PrefNames.POINTS_NAME, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.POINTS_NAME, value)
        }

    override var isLanguageChanged: Boolean
        get() = preferences.getBoolean(PrefNames.IS_LANGUAGE_CHANGED, false)
        set(value) {
            preferences.saveBoolean(PrefNames.IS_LANGUAGE_CHANGED, value)
        }

    override var defaultGroupId: String
        get() = preferences.getString(PrefNames.DEFAULT_GROUP_ID, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.DEFAULT_GROUP_ID, value)
        }

    override var defaultGroupName: String
        get() = preferences.getString(PrefNames.DEFAULT_GROUP_NAME, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.DEFAULT_GROUP_NAME, value)
        }

    override var defaultGroupIcon: String
        get() = preferences.getString(PrefNames.DEFAULT_GROUP_ICON, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.DEFAULT_GROUP_ICON, value)
        }
    override var selectedCountryId: String
        get() = preferences.getString(PrefNames.SELECTED_COUNTRY_ID, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.SELECTED_COUNTRY_ID, value)
        }

    override var countryIsoFromIp: String
        get() = preferences.getString(PrefNames.COUNTRY_ISO_FROM_IP, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.COUNTRY_ISO_FROM_IP, value)
        }

    override var timezone: String
        get() = preferences.getString(PrefNames.TIMEZONE, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.TIMEZONE, value)
        }

    override var userPoints: String
        get() = preferences.getString(PrefNames.USER_POINTS, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.USER_POINTS, value)
        }

    override var generalPurposeBudgetId: String
        get() = preferences.getString(PrefNames.GENERAL_PURPOSE_BUDGET_ID, "") ?: ""
        set(value) {
            preferences.saveString(PrefNames.GENERAL_PURPOSE_BUDGET_ID, value)
        }

    override var isSuperAdmin: Boolean
        get() = preferences.getBoolean(PrefNames.SUPER_ADMIN, false)
        set(value) {
            preferences.saveBoolean(PrefNames.SUPER_ADMIN, value)
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