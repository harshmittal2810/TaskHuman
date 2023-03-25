package com.harsh.taskhuman.di.module

import android.content.Context
import com.harsh.taskhuman.libraries.preferences.EncryptedPreferences
import com.harsh.taskhuman.libraries.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 13/07/22.
 **/
@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): Preferences {
        return EncryptedPreferences(applicationContext)
    }
}