package com.harsh.taskhuman.libraries.logger

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.harsh.taskhuman.BuildConfig
import com.harsh.taskhuman.libraries.logs.LocalLog
import com.harsh.taskhuman.libraries.logs.LogLevel
import com.harsh.taskhuman.libraries.logs.LogUtils.getLogFile
import com.harsh.taskhuman.utils.DateTimeUtility.getCurrentTimeInUTC
import timber.log.Timber
import java.io.BufferedWriter
import java.io.FileWriter

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
class LogFileWriterTree(
    private val applicationContext: Context
) : Timber.Tree() {

    companion object {
        private const val DEFAULT_LOG_DELIMITER = " "
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return BuildConfig.DEBUG || (priority == Log.DEBUG && LocalLog.DEBUG_LOGS) || (priority != Log.DEBUG && LocalLog.LOG)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority).not()) return

        writeLogsToFile(
            logLevel = getLogLevel(priority),
            title = tag,
            message = message
        )
    }

    private fun writeLogsToFile(
        logLevel: LogLevel = LogLevel.DEBUG,
        title: String?,
        message: String?
    ) {
        try {
            val logFile = getLogFile(applicationContext)
            val logTimeStamp = getCurrentTimeInUTC("yyyy-MM-dd HH:mm:ss.SSS")
            BufferedWriter(FileWriter(logFile, true)).use { logWriter ->
                logWriter.append("ANDROID")
                    .append(DEFAULT_LOG_DELIMITER)
                    .append(BuildConfig.VERSION_NAME)
                    .append(DEFAULT_LOG_DELIMITER)
                    .append(logLevel.name)
                    .append(DEFAULT_LOG_DELIMITER)
                    .append(logTimeStamp)
                    .append(DEFAULT_LOG_DELIMITER)
                    .append(title)
                    .append(DEFAULT_LOG_DELIMITER)
                    .append(message)
                    .append("\n")
                    .flush()
            }
        } catch (e: Throwable) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    private fun getLogLevel(priority: Int): LogLevel {
        return when (priority) {
            Log.VERBOSE -> LogLevel.VERBOSE
            Log.INFO -> LogLevel.INFO
            Log.WARN -> LogLevel.WARNING
            Log.ERROR -> LogLevel.ERROR
            else -> LogLevel.DEBUG
        }
    }
}