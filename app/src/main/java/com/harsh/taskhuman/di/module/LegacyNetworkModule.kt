package com.harsh.taskhuman.di.module

import android.content.Context
import com.harsh.taskhuman.common.sharepref.DefaultPrefHelper
import com.harsh.taskhuman.common.sharepref.PrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
@Module
@InstallIn(SingletonComponent::class)
object LegacyNetworkModule {

    @Provides
    @Singleton
    fun providePreferenceHelper(@ApplicationContext applicationContext: Context): PrefHelper {
        return DefaultPrefHelper.getInstance(applicationContext)
    }
}
