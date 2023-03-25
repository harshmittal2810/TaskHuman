package com.harsh.taskhuman.libraries.logs

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.harsh.taskhuman.BuildConfig
import timber.log.Timber

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
object LocalLog {
    @JvmField
    var LOG = BuildConfig.LOGGING_ENABLED

    @JvmField
    var DEBUG_LOGS = BuildConfig.ENABLE_DEBUGGING_LOGS

    @JvmStatic
    fun info(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).i("$message")
        }
    }

    @JvmStatic
    fun error(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).e("$message")
        }
    }

    @JvmStatic
    fun error(tag: String, message: String?, exception: Throwable?) {
        if (LOG) {
            Timber.tag(tag).e(exception, "$message")
        }
        exception?.let {
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }

    @JvmStatic
    fun debug(tag: String, message: String?) {
        if (LOG && DEBUG_LOGS) {
            Timber.tag(tag).d("$message")
        }
    }

    @JvmStatic
    fun verbose(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).v("$message")
        }
    }

    @JvmStatic
    fun warn(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).w("$message")
        }
    }
}