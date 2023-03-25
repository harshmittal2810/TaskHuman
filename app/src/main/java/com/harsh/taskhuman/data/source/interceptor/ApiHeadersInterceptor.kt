package com.harsh.taskhuman.data.source.interceptor

import com.harsh.taskhuman.BuildConfig
import com.harsh.taskhuman.MainApplication
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.isValidAndNotEmpty
import com.harsh.taskhuman.data.ApiHeaderConstants
import com.harsh.taskhuman.data.repository.ApiHeadersRepository
import com.harsh.taskhuman.language.LocaleManager
import com.harsh.taskhuman.utils.HelperUtility
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Harsh Mittal on 15/07/22.
 *
 * This class is responsible to add all the __headers__ to each request.
 **/
class ApiHeadersInterceptor(
    private val prefHelper: PrefHelper, private val apiHeadersRepository: ApiHeadersRepository
) : Interceptor {

    companion object {
        private const val TAG = "ApiHeadersInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest: Request = chain.request()
        var newUrl = HelperUtility.getBaseUrl(prefHelper)
        if (!newUrl.contains("chef")) {
            if (originalRequest.url.toUrl().path.contains("chef").not()) newUrl =
                newUrl + "/chef" + originalRequest.url.toUrl().path
            else newUrl += originalRequest.url.toUrl().path
        }

        originalRequest = originalRequest.newBuilder().url(newUrl).build()

        val requestBuilder = requestBuilderWithDefaultHeaders(originalRequest)
        return chain.proceed(requestBuilder.build())
    }

    private fun requestBuilderWithDefaultHeaders(originalRequest: Request): Request.Builder {
        val request = originalRequest.newBuilder()

        request.header(ApiHeaderConstants.HEADER_CONTENT_TYPE, ApiHeaderConstants.CONTENT_TYPE_JSON)
            .header(ApiHeaderConstants.HEADER_USER_AGENT, ApiHeaderConstants.USER_AGENT)
            .header(ApiHeaderConstants.HEADER_APP_VERSION_NAME, BuildConfig.VERSION_NAME)
            .header(ApiHeaderConstants.HEADER_APP_VERSION_CODE, BuildConfig.VERSION_CODE.toString())
            .header(
                ApiHeaderConstants.LANGUAGE,
                LocaleManager.getLanguagePref(MainApplication.instance())
            ).header(ApiHeaderConstants.PLATFORM, apiHeadersRepository.getPlatformCookie())

        if (apiHeadersRepository.getSafetyNetToken().isValidAndNotEmpty()) {
            request.header(
                ApiHeaderConstants.SAFETY_NET_TOKEN, apiHeadersRepository.getSafetyNetToken()
            )
        }

        apiHeadersRepository.getCookie().forEach {
            if (it.isValidAndNotEmpty()) request.header(ApiHeaderConstants.COOKIE, it)
        }

        return request

    }
}
