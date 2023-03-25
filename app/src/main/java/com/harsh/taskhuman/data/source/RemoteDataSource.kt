package com.harsh.taskhuman.data.source

import android.content.Context
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.util.getStringByLocale
import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.utils.HelperUtility

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
interface RemoteDataSource : FailureHandler {

    /**
     * Method to prepare the [Result] by executing the API request.
     */
    suspend fun <T> getResult(applicationContext: Context, api: suspend () -> T): Result<T> {
        return if (HelperUtility.internetCheck(applicationContext)) {
            try {
                Result.Success(api())
            } catch (e: Exception) {
                Result.Failure(parseNetworkError(applicationContext, e))
            }
        } else {
            Result.Failure(
                NetworkError(
                    100, applicationContext.getStringByLocale(
                        R.string.network_error_poor_internet
                    ), NetworkErrorKind.INTERNET
                )
            )
        }
    }

    /**
     * Method to prepare the [Result] by executing the API request.
     */
    suspend fun <NetworkModel, DomainModel> getResult(
        applicationContext: Context,
        api: suspend () -> NetworkModel,
        dataConverter: (NetworkModel) -> DomainModel
    ): Result<DomainModel> {
        return if (HelperUtility.internetCheck(applicationContext)) {
            try {
                Result.Success(dataConverter(api()))
            } catch (e: Exception) {
                Result.Failure(parseNetworkError(applicationContext, e))
            }
        } else {
            Result.Failure(
                NetworkError(
                    100,
                    applicationContext.getStringByLocale(R.string.network_error_poor_internet),
                    NetworkErrorKind.INTERNET
                )
            )
        }
    }
}