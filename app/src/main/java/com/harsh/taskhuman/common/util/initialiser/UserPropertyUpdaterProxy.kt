package com.harsh.taskhuman.common.util.initialiser

import com.harsh.taskhuman.common.util.initialiser.properties.User
import com.harsh.taskhuman.common.util.initialiser.properties.UserPropertyUpdater
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */

open class UserPropertyUpdaterProxy @Inject constructor(
    private val userPropertyUpdaters: List<UserPropertyUpdater>,
    private val coroutineContext: CoroutineContext
) : UserPropertyUpdater {

    override fun initUserProperty(user: User) {
        GlobalScope.launch(coroutineContext) {
            userPropertyUpdaters.forEach {
                it.initUserProperty(user)
            }
        }
    }

    override fun updateUserProperty(key: String, value: String) {
        GlobalScope.launch(coroutineContext) {
            userPropertyUpdaters.forEach {
                it.updateUserProperty(key, value)
            }
        }
    }

    override fun updateUserProperty(key: String, value: Boolean) {
        GlobalScope.launch(coroutineContext) {
            userPropertyUpdaters.forEach {
                it.updateUserProperty(key, value)
            }
        }
    }

}