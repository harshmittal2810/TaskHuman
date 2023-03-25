package com.harsh.taskhuman.common.util.initialiser.login

import com.harsh.taskhuman.libraries.logs.LocalLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */

@Singleton
class LoginNotifierImpl @Inject constructor() : LoginNotifier {

    private val loginNotifierStream = MutableStateFlow(false)

    override fun notify(isLoggedIn: Boolean) {
        LocalLog.debug(javaClass.simpleName, ">>>> notified $isLoggedIn")
        GlobalScope.launch {
            loginNotifierStream.value = isLoggedIn
        }
    }

    override fun loginNotifierStream(): StateFlow<Boolean> = loginNotifierStream
}