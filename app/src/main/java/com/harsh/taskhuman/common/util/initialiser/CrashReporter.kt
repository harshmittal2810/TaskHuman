package com.harsh.taskhuman.common.util.initialiser

import com.harsh.taskhuman.common.util.initialiser.properties.VerifiedEventAttributes

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */


interface CrashReporter {

    fun initializeCrashReporter()

    fun track(throwable: Throwable, verifiedEventAttributes: VerifiedEventAttributes? = null)
}