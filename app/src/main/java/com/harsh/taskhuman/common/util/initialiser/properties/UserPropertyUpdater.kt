package com.harsh.taskhuman.common.util.initialiser.properties

/**
 * Created by Harsh Mittal on 25/02/22 12:36 PM
 */

interface UserPropertyUpdater {

    /**
     * Initialize user property
     */
    fun initUserProperty(user: User)

    /**
     * Update user property with given key and value
     */
    fun updateUserProperty(key: String, value: String)

    /**
     * Update user property with given key and value
     */
    fun updateUserProperty(key: String, value: Boolean)

}