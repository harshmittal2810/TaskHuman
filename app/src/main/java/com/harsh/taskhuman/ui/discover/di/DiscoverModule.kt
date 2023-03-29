package com.harsh.taskhuman.ui.discover.di

import android.content.Context
import com.harsh.taskhuman.di.qualifier.Default
import com.harsh.taskhuman.di.qualifier.Local
import com.harsh.taskhuman.di.qualifier.Remote
import com.harsh.taskhuman.libraries.preferences.Preferences
import com.harsh.taskhuman.ui.discover.repository.DefaultDiscoverRepository
import com.harsh.taskhuman.ui.discover.repository.DiscoverRepository
import com.harsh.taskhuman.ui.discover.source.DiscoverDataSource
import com.harsh.taskhuman.ui.discover.source.local.DiscoverLocalDataSource
import com.harsh.taskhuman.ui.discover.source.remote.DiscoverRemoteDataSource
import com.harsh.taskhuman.ui.discover.source.remote.service.DiscoverApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

@Module
@InstallIn(ActivityRetainedComponent::class)
object DiscoverModule {

    @Provides
    fun provideDiscoverApiService(
        @Default retrofit: Retrofit
    ): DiscoverApiService {
        return retrofit.create(DiscoverApiService::class.java)
    }

    @Provides
    @Local
    fun provideDiscoverLocalDataSource(preferences: Preferences): DiscoverDataSource {
        return DiscoverLocalDataSource(preferences)
    }

    @Provides
    @Remote
    fun provideDashBoardRemoteDataSource(
        @ApplicationContext applicationContext: Context, api: DiscoverApiService
    ): DiscoverDataSource {
        return DiscoverRemoteDataSource(applicationContext, api)
    }

    @Provides
    fun provideDashBoardRepository(
        @Local local: DiscoverDataSource, @Remote remote: DiscoverDataSource
    ): DiscoverRepository {
        return DefaultDiscoverRepository(local, remote)
    }
}