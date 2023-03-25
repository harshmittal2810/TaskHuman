package com.xoxoday.reward.libraries.logger

/**
 * Created by Harsh Mittal on 01/07/22.
 */
interface Logger {

    fun verbose(tag: String, message: String?)

    fun debug(tag: String, message: String?)

    fun info(tag: String, message: String?)

    fun warn(tag: String, message: String?)

    fun error(tag: String, message: String?)

    fun error(tag: String, message: String?, exception: Throwable?)
}