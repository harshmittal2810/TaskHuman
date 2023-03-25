package com.harsh.taskhuman.libraries.logger

import timber.log.Timber

abstract class LogTree : Timber.Tree() {

    companion object {
        const val MAX_LOG_LENGTH = 4000
    }
}