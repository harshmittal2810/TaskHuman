package com.harsh.taskhuman.data.source.interceptor

import com.harsh.taskhuman.common.constants.StringConstants
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.data.ApiHeaderConstants
import com.harsh.taskhuman.data.repository.ApiHeadersRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Harsh Mittal on 15/07/22.
 *
 * This class is responsible to add all the __headers__ to each request.
 **/
class ApiHeadersInterceptor(
    private val prefHelper: PrefHelper,
    private val apiHeadersRepository: ApiHeadersRepository,
) : Interceptor {

    companion object {
        private const val TAG = "ApiHeadersInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = requestBuilderWithDefaultHeaders(chain.request())
        return chain.proceed(requestBuilder.build())
    }

    private fun requestBuilderWithDefaultHeaders(originalRequest: Request): Request.Builder {
        val request = originalRequest.newBuilder()

        request.header(ApiHeaderConstants.HEADER_ACCEPT, "*/*")
            .header(ApiHeaderConstants.HEADER_AUTHORIZATION, StringConstants.AUTHORIZATION_TOKEN)

        return request

    }
}
