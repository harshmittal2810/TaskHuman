package com.harsh.taskhuman.data.source.interceptor

import android.content.Intent
import com.harsh.taskhuman.MainApplication.Companion.instance
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.libraries.logs.LocalLog
import com.harsh.taskhuman.ui.LogoutPopupActivity
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Harsh Mittal on 20/07/22.
 *
 * This class is responsible to add all the __headers__ to each request.
 **/
class CookiesHeadersInterceptor(
    private val prefHelper: PrefHelper
) : Interceptor {

    companion object {
        private const val TAG = "ApiHeadersInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val rawJson = originalResponse.body!!.string()
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = HashSet<String>()
            for (header in originalResponse.headers("Set-Cookie")) {
                if (header.trim { it <= ' ' }
                        .substring(0, header.trim { it <= ' ' }.length.coerceAtMost(3))
                        .equals("a_t", ignoreCase = true)
                ) {
                    cookies.add(header)
                    prefHelper.cookies = cookies
                }
            }
        }

        val json: JSONObject
        try {
            json = JSONObject(rawJson)
            if (json.has("error_message_id")) {
                val errorCode = json.getString("error_message_id")
                if (errorCode.equals("auth.token_error", ignoreCase = true)) {
                    val openLogout = Intent(instance(), LogoutPopupActivity::class.java)
                    openLogout.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    instance().startActivity(openLogout)
                }
            }
        } catch (e: JSONException) {
            LocalLog.error(TAG, e.message, e)
        }
        return originalResponse.newBuilder()
            .body(rawJson.toResponseBody(originalResponse.body!!.contentType())).build()
    }
}
