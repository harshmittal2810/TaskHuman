package com.harsh.taskhuman.utils

import com.harsh.taskhuman.common.constants.StringConstants.LOCAL_DATE_FORMAT
import com.harsh.taskhuman.libraries.logs.LocalLog
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
object DateTimeUtility {

    private const val TAG = "DateTimeUtility"
    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS

    const val MEETING_DATE_FORMAT = "yyyy-MM-dd"
    const val TIME_FORMAT = "hh:mm a"
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    val SERVER_DATE_ENG_SDF = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
    val TIME_FORMAT_ENG_SDF = SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH)
    val LOCAL_DATE_ENG_SDF = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.ENGLISH)
    val MEETING_DATE_ENG_SDF = SimpleDateFormat(MEETING_DATE_FORMAT, Locale.ENGLISH)

    val MONTH_ENG_SDF = SimpleDateFormat("MM", Locale.ENGLISH)
    val DAY_ENG_SDF = SimpleDateFormat("dd", Locale.ENGLISH)
    val YEAR_ENG_SDF = SimpleDateFormat("yyyy", Locale.ENGLISH)
    const val IST_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private val IST_DATE_ENG_SDF = SimpleDateFormat(IST_DATE_FORMAT, Locale.ENGLISH)
    private const val IST_TIME_ZONE = "IST+05:30"

    fun getTimestampString(): String {
        val date = Calendar.getInstance()
        return SimpleDateFormat("yyyy MM dd hh mm ss", Locale.US).format(date.time).replace(" ", "")
    }

    fun getCurrentTimeInUTC(format: String): String {
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }

    fun getPollTime(time: Float): String {
        val days = floor(time / 24)
        if (time <= 1) {
            return "1 Hour remaining"
        }
        if (days <= 1) {
            return "1 Day remaining"
        }
        return "${Utils.getNumberWithoutDecimal(days)} Days remaining"
    }

    fun getLongTime(inputDate: String?): Long {
        val serverSDF = IST_DATE_ENG_SDF
        serverSDF.timeZone = TimeZone.getTimeZone(IST_TIME_ZONE)
        var timeInMilliseconds: Long = 0
        try {
            inputDate?.let {
                timeInMilliseconds = serverSDF.parse(it).time
            }
        } catch (e: ParseException) {
            LocalLog.error(TAG, e.message, e)
        }
        return timeInMilliseconds
    }
}