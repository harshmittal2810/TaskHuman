package com.harsh.taskhuman.data.source

import android.annotation.SuppressLint
import android.content.Context
import com.harsh.taskhuman.BuildConfig
import com.harsh.taskhuman.common.constants.StringConstants
import com.harsh.taskhuman.data.source.interceptor.ApiHeadersInterceptor
import com.harsh.taskhuman.data.source.interceptor.ApiLoggingInterceptor
import com.harsh.taskhuman.data.source.interceptor.CookiesHeadersInterceptor
import com.harsh.taskhuman.libraries.logs.LocalLog
import okhttp3.CertificatePinner
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by Harsh Mittal on 15/07/22.
 *
 * This class is responsible to create the [OkHttpClient] instance
 * with all the [Interceptor]'s and SSL pinning attached.
 **/
class ApiClient(
    private val applicationContext: Context,
    private val apiHeadersInterceptor: ApiHeadersInterceptor,
    private val cookiesHeadersInterceptor: CookiesHeadersInterceptor,
) {

    private companion object {
        private const val CERTIFICATE_PIN_PREFIX = "sha256/"
    }

    /**
     * Returns the [OkHttpClient] fresh instance.
     */

    fun getApiClient(): OkHttpClient {
        val httpClient: OkHttpClient.Builder =
            if (BuildConfig.DEBUG) {
                getUnsafeOkHttpClient()!!
            } else {
                getSafeOkHttpClient(disableSSLPinning = true)
            }

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        httpClient.dispatcher(dispatcher)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)

        httpClient.addInterceptor(ApiLoggingInterceptor(applicationContext))
        httpClient.addInterceptor(apiHeadersInterceptor)
        httpClient.addInterceptor(cookiesHeadersInterceptor)
        if (BuildConfig.NETWORK_LOGGING_ENABLED) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        return httpClient.build()
    }

    /**
     * Returns the [OkHttpClient] fresh instance with out apiHeadersInterceptor and tokenAuthenticator.
     */
    fun getApiClientForJioEngage(): OkHttpClient {
        val httpClient: OkHttpClient.Builder = if (BuildConfig.DEBUG) {
            getUnsafeOkHttpClient()!!
        } else {
            getSafeOkHttpClient(disableSSLPinning = true)
        }

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        httpClient.dispatcher(dispatcher)
        httpClient.addInterceptor(ApiLoggingInterceptor(applicationContext))
        if (BuildConfig.NETWORK_LOGGING_ENABLED) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        return httpClient.build()
    }

    /**
     * Returns the [OkHttpClient] instance with [CertificatePinner] added.
     */
    private fun getSafeOkHttpClient(disableSSLPinning: Boolean): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        if (disableSSLPinning) {
            return builder
        }
        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        tmf.init(null as KeyStore?)
        var x509Tm: X509TrustManager? = null
        for (tm in tmf.trustManagers) {
            if (tm is X509TrustManager) {
                x509Tm = tm
                break
            }
        }
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, arrayOf<TrustManager?>(x509Tm), null)
        val certificatePinner = CertificatePinner.Builder()
            .add(StringConstants.IDC_API, StringConstants.CERTIFICATE_FINGERPRINTS)
            .build()
        builder.hostnameVerifier { hostname, _ ->
            hostname.contains(StringConstants.IDC_API)
        }
        builder.certificatePinner(certificatePinner)
        return builder
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, (trustAllCerts[0] as X509TrustManager))
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Pins certificates for `pattern`.
     *
     * @param pattern lower-case host name or wildcard pattern such as `*.example.com`.
     * @param pins SHA-256 or SHA-1 hashes. Each pin is a hash of a certificate's Subject Public Key
     *     Info, base64-encoded and prefixed with either `sha256/` or `sha1/`.
     */
    private fun CertificatePinner.Builder.add(pattern: String, pins: Array<String>) = apply {
        for (pin in pins) {
            val patternUrl = pattern.substringBefore("/")
            LocalLog.error("ApiClient", "Pin $pin , $pattern")
            this.pins.add(
                CertificatePinner.Pin(
                    patternUrl,
                    if (pin.startsWith(CERTIFICATE_PIN_PREFIX).not()) {
                        CERTIFICATE_PIN_PREFIX + pin
                    } else {
                        pin
                    }
                )
            )
        }
    }
}