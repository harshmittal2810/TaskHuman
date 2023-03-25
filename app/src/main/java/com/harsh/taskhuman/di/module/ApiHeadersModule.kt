package com.harsh.taskhuman.di.module

import android.content.Context
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.data.repository.ApiHeadersRepository
import com.harsh.taskhuman.data.repository.DefaultApiHeadersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by Harsh Mittal on 19/07/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiHeadersModule {
    @Provides
    fun provideTokenRepository(
        @ApplicationContext context: Context,
        prefHelper: PrefHelper,
    ): ApiHeadersRepository {
        return DefaultApiHeadersRepository(context, prefHelper)
    }
}
