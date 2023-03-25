package com.harsh.taskhuman.libraries.crashreport

/**
 * Created by Harsh Mittal on 11/07/22.
 *
 * CrashReporter is responsible to catch the uncaught exceptions.
 **/
object CrashReporter {

    /**
     * Initializes the [CrashReporter] to be invoked when a thread abruptly
     * terminates due to an uncaught exception.
     */
    @JvmStatic
    fun install() {
        if (Thread.getDefaultUncaughtExceptionHandler() !is CrashReporterExceptionHandler) {
            Thread.setDefaultUncaughtExceptionHandler(CrashReporterExceptionHandler())
        }
    }
}