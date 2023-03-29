package com.harsh.taskhuman.common.sharepref

import android.content.Context

/**
 * Interface between the developer and the SharedPreference file
 *
 * Created by Harsh Mittal on 20/06/22.
 */
interface PrefHelper {
    var envBaseUrl: String
    var isLogin: Boolean
    var gcmToken: String
    var isForegroundApp: Boolean

    var featureConfigStateRepo: Boolean
    var cookies: HashSet<String>
    var safetyNetToken: String

    fun clearSharedPref()

    companion object {
        fun getInstance(applicationContext: Context): PrefHelper {
            return DefaultPrefHelper.getInstance(applicationContext)
        }
    }
}