package com.harsh.taskhuman.data.source

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
data class NetworkError(
    val errorCode: Int,
    val errorMessage: String,
    val errorKind: NetworkErrorKind = NetworkErrorKind.HTTP,
    val extras: Any? = null
)