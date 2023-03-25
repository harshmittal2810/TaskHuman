package com.harsh.taskhuman.common.util

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.harsh.taskhuman.common.constants.StringConstants
import com.harsh.taskhuman.language.LocaleManager
import com.harsh.taskhuman.utils.DateTimeUtility
import java.net.URL
import java.net.URLDecoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String?.isValidAndNotEmpty(): Boolean {
    if (this == null) {
        return false
    }
    if (this.trim().isEmpty()) {
        return false
    }

    if (this == "null") {
        return false
    }
    return this.trim().isNotEmpty()
}

fun String?.isNotValidOrEmpty(): Boolean {
    return isValidAndNotEmpty().not()
}

fun String?.isValidEmailId(): Boolean {
    if (this == null) {
        return false
    }
    val pattern = Pattern.compile("^[^@]+@[a-zA-Z0-9._-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String?.toCapitalize(): String {
    if (this == null) return ""
    return this.lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun String.removeAllStart(remove: String): String {
    if (this.isEmpty() || remove.isEmpty()) {
        return this
    }
    return if (this.startsWith(remove)) {
        val result = this.substring(remove.length)
        if (result.startsWith(remove)) {
            result.removeAllStart(remove)
        } else {
            result
        }
    } else this
}

fun String.isNameLengthValid(): Boolean {
    return this.trim().length > 1
}

fun String.getMonthFromDate(): String {
    val format1 =
        SimpleDateFormat(StringConstants.LOCAL_DATE_FORMAT, LocaleManager.getCurrentLocale())
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.MONTH_ENG_SDF
    return format2.format(dt1)

}

fun String.getMonthFromEndDate(): String {
    val format1 = DateTimeUtility.MEETING_DATE_ENG_SDF
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.MONTH_ENG_SDF
    return format2.format(dt1)

}

fun String.getDayFromDate(): String {
    val format1 =
        SimpleDateFormat(StringConstants.LOCAL_DATE_FORMAT, LocaleManager.getCurrentLocale())
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.DAY_ENG_SDF
    return format2.format(dt1)
}

fun String.getTimeDifferenceFromNow(): Int {
    val timeZone = TimeZone.getTimeZone("IST+05:30")
    val serverSDF =
        SimpleDateFormat(DateTimeUtility.SERVER_DATE_FORMAT, LocaleManager.getCurrentLocale())
    serverSDF.timeZone = timeZone
    val calendar = Calendar.getInstance(timeZone)
    val dt1 = serverSDF.parse(this) ?: calendar.time
    val diff: Long = dt1.time - calendar.time.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    return minutes.toInt()
}

fun String.getDayFromEndDate(): String {
    val format1 = DateTimeUtility.MEETING_DATE_ENG_SDF
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.DAY_ENG_SDF
    return format2.format(dt1)
}

fun String.getYearFromDate(): String {
    val format1 =
        SimpleDateFormat(StringConstants.LOCAL_DATE_FORMAT, LocaleManager.getCurrentLocale())
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.YEAR_ENG_SDF
    return format2.format(dt1)
}

fun String.getYearFromEndDate(): String {
    val format1 = DateTimeUtility.MEETING_DATE_ENG_SDF
    val dt1 = format1.parse(this) ?: Date()
    val format2: DateFormat = DateTimeUtility.YEAR_ENG_SDF
    return format2.format(dt1)
}

fun String.replaceAllWhiteSpaces(): String {
    return this.replace("[\\n\\t\\s ]".toRegex(), "")
}

fun String.removeAllSuffix(remove: String): String {
    if (this.isEmpty() || remove.isEmpty()) {
        return this
    }
    return if (this.endsWith(remove)) {
        val result = this.substring(0, this.length - 1)
        if (result.endsWith(remove)) {
            result.removeAllSuffix(remove)
        } else {
            result
        }
    } else this
}

fun String.parseHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

fun Context.getStringByLocale(@StringRes id: Int): String {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(LocaleManager.getCurrentLocale())
    return createConfigurationContext(configuration).resources.getString(id)
}

fun splitQuery(url: URL): Map<String, String> {
    val queryPairs: MutableMap<String, String> = LinkedHashMap()
    val query = url.query
    val pairs = query.split("&").toTypedArray()
    for (pair in pairs) {
        val idx = pair.indexOf("=")
        queryPairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] =
            URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
    }
    return queryPairs
}

fun getSSOToken(uri: Uri?): String {
    if (uri == null) {
        return ""
    }
    val arrSplit = uri.path?.split("/")?.toTypedArray()

    return if (arrSplit.isNullOrEmpty().not()) {
        arrSplit!![arrSplit.size - 1]
    } else ""
}

fun fromHtml(source: String?): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }
}

fun SpannableString.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this.toString())
    }
}