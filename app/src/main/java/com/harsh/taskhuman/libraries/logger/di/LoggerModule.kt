package com.harsh.taskhuman.libraries.logger.di

import com.harsh.taskhuman.libraries.logger.DefaultLogger
import com.harsh.taskhuman.libraries.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
@InstallIn(SingletonComponent::class)
@Module
object LoggerModule {

    @Singleton
    @Provides
    fun provideLogger(): Logger {
        return DefaultLogger()
    }
}