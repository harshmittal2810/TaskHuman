package com.harsh.taskhuman.data.source

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
class HttpException(
    url: String,
    errorCode: Int
) : Exception("HTTP $errorCode Failed to fetch response from $url!")