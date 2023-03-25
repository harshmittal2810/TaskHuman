package com.harsh.taskhuman.data

import com.harsh.taskhuman.data.source.NetworkError


/**
 * Created by Harsh Mittal on 15/07/22.
 **/
sealed class Result<out T> {

    data class Success<R>(val data: R?) : Result<R>()

    data class Failure(val networkError: NetworkError) : Result<Nothing>()

    object Loading : Result<Nothing>()

    object Empty : Result<Nothing>()
}