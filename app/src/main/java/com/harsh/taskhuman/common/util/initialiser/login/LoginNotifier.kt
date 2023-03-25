package com.harsh.taskhuman.common.util.initialiser.login

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */

interface LoginNotifier {
    fun notify(isLoggedIn: Boolean)
    fun loginNotifierStream(): StateFlow<Boolean>
}