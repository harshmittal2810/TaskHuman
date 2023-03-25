package com.harsh.taskhuman.di.module

import com.harsh.taskhuman.common.util.initialiser.*
import com.harsh.taskhuman.common.util.initialiser.login.LoginNotifier
import com.harsh.taskhuman.common.util.initialiser.login.LoginNotifierImpl
import com.harsh.taskhuman.common.util.initialiser.properties.UserPropertyUpdater
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class MainManagerModule {

    @Provides
    fun provideCrashReporters(
        firebaseCrashlyticsTracker: FirebaseCrashlyticsTracker,
    ): List<@JvmWildcard CrashReporter> {
        return listOf(firebaseCrashlyticsTracker)
    }

    @Provides
    fun provideUserPropertyUpdaters(
        firebaseCrashlyticsTracker: FirebaseCrashlyticsTracker,
    ): List<@JvmWildcard UserPropertyUpdater> {
        return listOf(firebaseCrashlyticsTracker)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        val job = Job()
        return job + Dispatchers.Default
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binding {

        @Binds
        fun provideToolsInitializer(toolsInitializerImpl: ToolsInitializerImpl): ToolsInitializer

        @Binds
        fun provideLoginNotifier(loginNotifierImpl: LoginNotifierImpl): LoginNotifier

        @Binds
        fun provideFirebaseTokenInitializer(firebaseTokenInitializerImpl: FirebaseTokenInitializerImpl): FirebaseInitializer

    }
}