package com.harsh.taskhuman.libraries.crashreport

import com.harsh.taskhuman.libraries.logs.LocalLog

/**
 * Created by Harsh Mittal on 11/07/22.
 *
 * [CrashReporterExceptionHandler] is responsible to catch the uncaught exception
 * and save to a file.
 **/
class CrashReporterExceptionHandler : Thread.UncaughtExceptionHandler {

    private companion object {
        private const val LOG_TAG = "CrashReporterExceptionHandler"
    }

    private var exceptionHandler: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    /**
     * Method invoked when the given [thread] terminates due to the
     * given uncaught exception.
     */
    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        LocalLog.error(LOG_TAG, "uncaughtException", throwable)
        exceptionHandler?.uncaughtException(thread, throwable)
    }
}