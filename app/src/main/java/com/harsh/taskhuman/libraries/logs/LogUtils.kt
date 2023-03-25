package com.harsh.taskhuman.libraries.logs

import android.content.Context
import com.harsh.taskhuman.utils.FileUtility.getFileDirectory
import java.io.File

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
object LogUtils {

    private const val DEFAULT_LOG_FILE_SIZE_THRESHOLD: Int = 500 * 1000
    private const val DEFAULT_LOG_FILE_NAME = "xoxoday_logs.txt"

    @JvmStatic
    fun getLogFile(applicationContext: Context): File {
        val directory = getLogDirectory(applicationContext)
        val logFile = File(directory, DEFAULT_LOG_FILE_NAME)
        if (logFile.length() >= DEFAULT_LOG_FILE_SIZE_THRESHOLD) {
            val target = File(directory, "${System.currentTimeMillis()}.txt")
            logFile.copyTo(target)
            logFile.delete()
        }
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        return logFile
    }

    fun copyAndDeleteCurrentLogFile(applicationContext: Context) {
        val directory = getLogDirectory(applicationContext)
        val logFile = File(directory, DEFAULT_LOG_FILE_NAME)

        if (logFile.exists() && logFile.length() > 0) {
            val target = File(directory, "${System.currentTimeMillis()}.txt")
            logFile.copyTo(target)
            logFile.delete()
        }
    }

    @JvmStatic
    fun getLogDirectory(applicationContext: Context): File {
        val directory = File(getFileDirectory(applicationContext), "xoxoday_logs")
        directory.mkdirs()
        return directory
    }

    fun deleteLogFiles(applicationContext: Context) {
        try {
            val logDirectory = getLogDirectory(applicationContext)
            if (logDirectory.exists()) {
                logDirectory.listFiles()?.forEach {
                    it.deleteOnExit()
                }
            }
        } catch (e: Exception) {
            LocalLog.error("LogUtils", "Error in deleting app logs", e)
        }
    }
}