package com.harsh.taskhuman.data.source.interceptor

import android.content.Context
import com.harsh.taskhuman.R
import com.harsh.taskhuman.data.ApiHeaderConstants
import com.harsh.taskhuman.data.source.HttpException
import com.harsh.taskhuman.data.source.HttpStatus
import com.harsh.taskhuman.libraries.logs.LocalLog
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import java.net.SocketTimeoutException

/**
 * Created by Harsh Mittal on 15/07/22.
 *
 * [ApiLoggingInterceptor] is responsible to log the request information and
 * response code.
 **/
class ApiLoggingInterceptor(
    private val applicationContext: Context
) : Interceptor {

    companion object {
        private const val TAG = "ApiLoggingInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = try {
            chain.proceed(chain.request())
        } catch (socketException: SocketTimeoutException) {
            buildExceptionResponse(
                chain,
                HttpStatus.SOCKET_TIMEOUT,
                R.string.network_error_socket_connection_failure
            )
        } catch (ioException: IOException) {
            buildExceptionResponse(
                chain,
                HttpStatus.IO_EXCEPTION,
                R.string.network_error_io_exception
            )
        } catch (unknown: Exception) {
            buildExceptionResponse(
                chain,
                HttpStatus.UNKNOWN,
                R.string.network_error_unknown_in_log_interceptor
            )
        }

        val url = response.request.url.encodedPath
        if (response.isSuccessful.not()) {
            LocalLog.error(TAG, "Failed to fetch response!", HttpException(url, response.code))
        }
        return response
    }

    private fun buildExceptionResponse(
        chain: Interceptor.Chain,
        code: Int,
        exception: Int
    ): Response {
        val message = applicationContext.getString(exception)
        val body = "{errors: \"$message\"}"
            .toResponseBody(ApiHeaderConstants.CONTENT_TYPE_JSON.toMediaType())
        return Response.Builder()
            .code(code)
            .protocol(Protocol.HTTP_2)
            .message(message)
            .body(body)
            .request(chain.request())
            .build()
    }
}