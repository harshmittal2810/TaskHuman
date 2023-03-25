package com.harsh.taskhuman.common.util.initialiser.properties

/**
 * Created by Harsh Mittal on 25/02/22 12:36 PM
 */

data class User constructor(
    val userId: String,
    val userName: String,
    val email: String,
    val mobileNumber: String,
    val userType: String = "",
)