package com.harsh.taskhuman.common.util.initialiser

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.harsh.taskhuman.common.util.initialiser.properties.User
import com.harsh.taskhuman.common.util.initialiser.properties.UserPropertyUpdater
import com.harsh.taskhuman.common.util.initialiser.properties.VerifiedEventAttributes
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 25/02/22 12:36 PM
 */

@Singleton
class FirebaseCrashlyticsTracker @Inject constructor() : CrashReporter, UserPropertyUpdater {

    override fun initializeCrashReporter() {
        FirebaseCrashlytics.getInstance()
    }

    override fun track(throwable: Throwable, verifiedEventAttributes: VerifiedEventAttributes?) {
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }

    override fun initUserProperty(user: User) {
        FirebaseCrashlytics.getInstance().apply {
            setUserId(user.userId)
            setCustomKey("username", user.userName)
            setCustomKey("userType", user.userType)
            setCustomKey("userNumber", user.mobileNumber)
            setCustomKey("userEmail", user.email)
        }
    }

    override fun updateUserProperty(key: String, value: String) {
        FirebaseCrashlytics.getInstance().apply {
            setCustomKey(key, value)
        }
    }

    override fun updateUserProperty(key: String, value: Boolean) {
        FirebaseCrashlytics.getInstance().apply {
            setCustomKey(key, value)
        }
    }
}