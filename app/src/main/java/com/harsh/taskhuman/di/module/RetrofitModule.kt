package com.harsh.taskhuman.di.module

import android.content.Context
import com.google.gson.Gson
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.data.repository.ApiHeadersRepository
import com.harsh.taskhuman.data.source.ApiClient
import com.harsh.taskhuman.data.source.interceptor.ApiHeadersInterceptor
import com.harsh.taskhuman.data.source.interceptor.CookiesHeadersInterceptor
import com.harsh.taskhuman.di.qualifier.Default
import com.harsh.taskhuman.di.qualifier.Token
import com.harsh.taskhuman.utils.HelperUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 13/07/22.
 **/
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    @Default
    fun provideDefaultRetrofit(@Default client: OkHttpClient, prefHelper: PrefHelper): Retrofit =
        Retrofit.Builder().baseUrl(HelperUtility.getBaseUrl(prefHelper)).client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson())).build()

    @Singleton
    @Provides
    @Token
    fun provideTokenRetrofit(@Token client: OkHttpClient, prefHelper: PrefHelper): Retrofit =
        Retrofit.Builder().baseUrl(HelperUtility.getBaseUrl(prefHelper)).client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson())).build()

    @Provides
    @Default
    @Singleton
    fun provideDefaultOkHttpClient(
        @ApplicationContext applicationContext: Context,
        apiHeadersInterceptor: ApiHeadersInterceptor,
        cookiesHeadersInterceptor: CookiesHeadersInterceptor,
    ): OkHttpClient {
        return ApiClient(
            applicationContext, apiHeadersInterceptor, cookiesHeadersInterceptor
        ).getApiClient()
    }


    @Provides
    @Singleton
    fun provideApiHeadersInterceptor(
        prefHelper: PrefHelper, apiHeadersRepository: ApiHeadersRepository
    ): ApiHeadersInterceptor {
        return ApiHeadersInterceptor(prefHelper, apiHeadersRepository)
    }

    @Provides
    @Singleton
    fun provideCookiesHeadersInterceptor(
        prefHelper: PrefHelper,
    ): CookiesHeadersInterceptor {
        return CookiesHeadersInterceptor(prefHelper)
    }
}
