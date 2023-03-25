package com.harsh.taskhuman.libraries.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.harsh.taskhuman.libraries.logs.LocalLog.DEBUG_LOGS
import com.harsh.taskhuman.libraries.logs.LocalLog.LOG
import timber.log.Timber

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
class DefaultLogger : Logger {

    override fun verbose(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).v("$message")
        }
    }

    override fun debug(tag: String, message: String?) {
        if (LOG && DEBUG_LOGS) {
            Timber.tag(tag).d("$message")
        }
    }

    override fun info(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).i("$message")
        }
    }

    override fun warn(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).w("$message")
        }
    }

    override fun error(tag: String, message: String?) {
        if (LOG) {
            Timber.tag(tag).e("$message")
        }
    }

    override fun error(tag: String, message: String?, exception: Throwable?) {
        if (LOG) {
            Timber.tag(tag).e(exception, "$message")
            exception?.let {
                FirebaseCrashlytics.getInstance().recordException(exception)
            }
        }
    }
}