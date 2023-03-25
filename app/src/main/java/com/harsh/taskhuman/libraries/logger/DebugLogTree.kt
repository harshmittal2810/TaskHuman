package com.harsh.taskhuman.libraries.logger

import android.os.Build
import android.util.Log

class DebugLogTree : LogTree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        // Workaround for devices that doesn't show lower priority logs
        var priority1 = priority
        if (Build.MANUFACTURER == "HUAWEI" || Build.MANUFACTURER == "samsung") {
            if (priority1 == Log.VERBOSE || priority1 == Log.DEBUG || priority1 == Log.INFO) priority1 =
                Log.ERROR
        }

        if (message.length < MAX_LOG_LENGTH) {
            if (priority1 == Log.ASSERT) {
                Log.wtf(tag, message)
            } else {
                Log.println(priority1, "Empuls/$tag", message)
            }
            return
        }

        // Split by line, then ensure each line can fit into Log's maximum length.
        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = Math.min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                if (priority1 == Log.ASSERT) {
                    Log.wtf(tag, part)
                } else {
                    Log.println(priority1, tag, part)
                    println("$tag: $part]")

                }
                i = end
            } while (i < newline)
            i++
        }
    }
}