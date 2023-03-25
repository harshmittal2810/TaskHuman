package com.harsh.taskhuman.common.sharepref

import android.content.Context

/**
 * Interface between the developer and the SharedPreference file
 *
 * Created by Harsh Mittal on 20/06/22.
 */
interface PrefHelper {
    var envBaseUrl: String

    var onBoardingPopupSkipped: Boolean
    var isLogin: Boolean
    var gcmToken: String
    var isForegroundApp: Boolean

    //    var deviceLocale: String
    var featureConfigStateRepo: Boolean
    var cookies: HashSet<String>
    var deepLinkPattern: HashSet<String>
    var safetyNetToken: String

    //user details
    var firstName: String
    var lastName: String
    var userId: String
    var designation: String
    var profileImage: String
    var email: String
    var subDomain: String
    var empulsRedemptionStatus: String
    var countryId: String
    var companyLogo: String
    var navigationColor: String
    var badgeColor: String
    var textColor: String
    var hoverColor: String
    var pointsName: String

    var backgroundColor: String
    var defaultGroupId: String
    var defaultGroupName: String
    var defaultGroupIcon: String
    var selectedCountryId: String
    var countryIsoFromIp: String
    var timezone: String
    var userPoints: String
    var generalPurposeBudgetId: String

    var isSuperAdmin: Boolean
    var isAwardAllowed: Boolean
    var isWishBoardAccess: Boolean
    var viewLeaderboardAccess: Boolean
    var createBudgetAccess: Boolean
    var isLanguageChanged: Boolean

    fun clearSharedPref()

    companion object {
        fun getInstance(applicationContext: Context): PrefHelper {
            return DefaultPrefHelper.getInstance(applicationContext)
        }
    }
}