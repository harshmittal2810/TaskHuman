package com.harsh.taskhuman.featureconfig.remoteconfig

import com.google.android.gms.tasks.Tasks
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.featureconfig.FeatureConfig
import com.harsh.taskhuman.featureconfig.FeatureConfigManager
import dagger.Lazy
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 15/07/22
 */

@Singleton
open class FirebaseRemoteConfigImpl @Inject constructor(
    private val remoteConfigInitialiser: Lazy<RemoteConfigInitializer>,
    private val prefHelper: PrefHelper
) : FeatureConfig, FeatureConfigManager {

    private val firebaseRemoteConfig by lazy { FirebaseRemoteConfig.getInstance() }
    private val subject = MutableStateFlow(false)

    override fun init() {
        Timber.d("#### initialising remote config")
        remoteConfigInitialiser.get().init(firebaseRemoteConfig)
    }

    override fun reset() {
        firebaseRemoteConfig.reset()
            .continueWith {
                Tasks.call {
                    prefHelper.featureConfigStateRepo = true
                    remoteConfigInitialiser.get().scheduleImmediateRefresh()
                }
            }
    }

    override fun getString(key: String, default: String): String {
        val value = firebaseRemoteConfig.getString(key)
        Timber.d("Firebase Config: $key, $default, $value")
        return if (value == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING) default
        else value
    }

    override fun getDouble(key: String, default: Double): Double {
        val value = firebaseRemoteConfig.getDouble(key)
        Timber.d("Firebase Config: $key, $default, $value")
        return if (value == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) default
        else value
    }

    override fun getLong(key: String, default: Long): Long {
        val value = firebaseRemoteConfig.getLong(key)
        Timber.d("Firebase Config: $key, $default, $value")
        return if (value == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) default
        else value
    }


    override fun getBoolean(key: String, default: Boolean): Boolean {
        val value = firebaseRemoteConfig.getBoolean(key)
        Timber.d("Firebase Config: $key, $default, $value")
        return if (value == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BOOLEAN) default
        else value
    }

    override fun observable() = subject

    override fun notifyObservable() {
        Timber.d("#### notify remote config stuff")
        subject.value = true
    }
}