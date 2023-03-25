package com.harsh.taskhuman.data.source

import android.content.Context
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.util.AppExceptionHandler
import com.harsh.taskhuman.libraries.logs.LocalLog
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
interface FailureHandler {

    private companion object {
        private const val TAG = "FailureHandler"
    }

    fun parseNetworkError(applicationContext: Context, error: Throwable): NetworkError {
        return when (error) {
            is HttpException -> {
                parseHttpError(applicationContext, error)
            }

            is SocketTimeoutException -> {
                NetworkError(
                    HttpStatus.SOCKET_TIMEOUT,
                    applicationContext.getString(R.string.network_error_socket_connection_failure),
                    NetworkErrorKind.SOCKET_TIMEOUT
                )
            }

            is IOException -> {
                NetworkError(
                    HttpStatus.IO_EXCEPTION,
                    applicationContext.getString(R.string.network_error_io_exception),
                    NetworkErrorKind.IO
                )
            }

            else -> {
                NetworkError(
                    HttpStatus.UNKNOWN,
                    applicationContext.getString(R.string.network_error_unknown),
                    NetworkErrorKind.UNKNOWN
                )
            }
        }
    }

    /**
     * Method to handle all the API failure cases.
     */
    fun parseHttpError(applicationContext: Context, error: HttpException): NetworkError {
        val response = error.response() ?: return NetworkError(
            HttpStatus.NO_RESPONSE, applicationContext.getString(R.string.network_error_no_response)
        )

        val errorBody = response.errorBody()?.string()

        if (errorBody.isNullOrBlank()) return NetworkError(
            HttpStatus.BLANK_RESPONSE,
            applicationContext.getString(R.string.network_error_no_error_body)
        )

        try {
            val errorObject = JSONObject(errorBody)
            LocalLog.info(TAG, "Error body = $errorObject")

            if (errorObject.has("errors")) {
                return when {
                    errorObject.opt("errors") is String -> {
                        var webinarData: HashMap<String, Any>? = null
                        if (errorObject.has("isWebinar") && errorObject.has("scheduledStartTime")) {
                            webinarData = HashMap()
                            webinarData["isWebinar"] = errorObject.optBoolean("isWebinar")
                            webinarData["scheduledStartTime"] =
                                errorObject.optString("scheduledStartTime")
                        }
                        NetworkError(
                            error.code(),
                            errorObject.optString("errors"),
                            NetworkErrorKind.SERVER,
                            webinarData
                        )
                    }
                    errorObject.opt("errors") is JSONObject -> {
                        val errorInnerObject = errorObject.optJSONObject("errors")
                        NetworkError(
                            error.code(),
                            errorInnerObject?.optString("errors")
                                ?: applicationContext.getString(R.string.network_error_wrong_error_body),
                            NetworkErrorKind.SERVER
                        )
                    }
                    else -> {
                        NetworkError(
                            error.code(),
                            applicationContext.getString(R.string.network_error_wrong_error_body),
                            NetworkErrorKind.HTTP
                        )
                    }
                }
            } else if (errorObject.has("message")) {
                return NetworkError(
                    error.code(), errorObject.optString("message"), NetworkErrorKind.SERVER
                )
            }
            return NetworkError(
                error.code(),
                applicationContext.getString(R.string.network_error_wrong_error_body),
                NetworkErrorKind.HTTP
            )
        } catch (e: JSONException) {
            AppExceptionHandler.handle(e)
            return NetworkError(
                error.code(),
                applicationContext.getString(R.string.network_error_response_parsing),
                NetworkErrorKind.RESPONSE_PARSING
            )
        }
    }
}