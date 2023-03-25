package com.harsh.taskhuman.featureconfig.di

import com.harsh.taskhuman.featureconfig.FeatureConfig
import com.harsh.taskhuman.featureconfig.FeatureConfigManager
import com.harsh.taskhuman.featureconfig.remoteconfig.FirebaseRemoteConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Harsh Mittal on 15/07/22
 */


@Module
@InstallIn(SingletonComponent::class)
interface FeatureConfigModule {

    @Binds
    fun providesFeatureConfig(impl: FirebaseRemoteConfigImpl): FeatureConfig

    @Binds
    fun providesFeatureConfigManager(impl: FirebaseRemoteConfigImpl): FeatureConfigManager

}