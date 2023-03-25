package com.harsh.taskhuman.common.util.initialiser

import android.content.Context
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.harsh.taskhuman.featureconfig.FeatureConfig
import com.harsh.taskhuman.featureconfig.FeatureConfigManager
import com.harsh.taskhuman.libraries.logs.LocalLog
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 25/02/22 12:36 PM
 */

@Singleton
class ToolsInitializerImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val crashReporters: List<CrashReporter>,
    private val firebaseInitializer: FirebaseInitializer,
    private val featureConfig: FeatureConfig,
    private val featureConfigManager: FeatureConfigManager,
) : ToolsInitializer {

    override fun initTools() {
        LocalLog.debug(javaClass.simpleName, ">>>> Tools initializer impl")

        //Handle enable/disable crashes
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)

        initFeatureConfigManager()
        initTrackers()
        remoteInit()

        firebaseInitializer.initializeToken()
    }

    private fun initTrackers() {
        LocalLog.debug(javaClass.simpleName, ">>>> Init Trackers")
        crashReporters.forEach {
            it.initializeCrashReporter()
        }
    }

    private fun initFeatureConfigManager() {
        Timber.d("#### From Delayed Initializer -- init feature config")
        featureConfigManager.init()
    }

    private fun remoteInit() {
        featureConfig.observable()
    }
}

interface ToolsInitializer {
    fun initTools()
}