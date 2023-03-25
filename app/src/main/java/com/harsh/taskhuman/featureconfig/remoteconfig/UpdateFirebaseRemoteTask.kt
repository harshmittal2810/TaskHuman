package com.harsh.taskhuman.featureconfig.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.initialiser.CrashReporter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Harsh Mittal on 15/07/22
 */

class UpdateFirebaseRemoteTask @Inject constructor(
    private val prefHelper: PrefHelper,
    private val crashReporters: List<CrashReporter>
) {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.d("CoroutineExceptionHandler got $exception")
        crashReporters.forEach {
            it.track(exception)
        }
    }

    fun refreshToken(forceRefresh: Boolean = false) {
        GlobalScope.launch(handler) {
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            val cacheExpiration = if (forceRefresh) 0 else CACHE_EXPIRATION_SECONDS
            try {
                val configSettings = FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(cacheExpiration)
                    .build()
                remoteConfig.setConfigSettingsAsync(configSettings)
                remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
                remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                    if (task.isComplete) {
                        Timber.d("#### Fetching new remote configs from Firebase with cache expiration $cacheExpiration")
                        prefHelper.featureConfigStateRepo = false
                        Timber.i("#### Remote config fetched successfully")
                    } else {
                        Timber.e("#### Failed to fetch remote config")
                    }
                }
                Timber.d("#### just loaded config: ${remoteConfig.all}")
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.tag(javaClass.simpleName).e(e)
            }
        }
    }

    companion object {
        const val CACHE_EXPIRATION_SECONDS: Long = 2 * 60 * 60
    }

}