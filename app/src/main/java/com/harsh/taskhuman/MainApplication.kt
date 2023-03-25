package com.harsh.taskhuman

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.LoginStateStream
import com.harsh.taskhuman.common.util.initialiser.ToolsInitializer
import com.harsh.taskhuman.language.LocaleManager.setLocale
import com.harsh.taskhuman.libraries.crashreport.CrashReporter
import com.harsh.taskhuman.libraries.logger.DebugLogTree
import com.harsh.taskhuman.libraries.logger.LogFileWriterTree
import com.harsh.taskhuman.libraries.logs.LocalLog
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : MultiDexApplication(), LifecycleEventObserver, Configuration.Provider {

    @Inject
    lateinit var preferences: PrefHelper

    @Inject
    lateinit var toolsInitializer: ToolsInitializer

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setMinimumLoggingLevel(Log.INFO).build()
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Timber.plant(DebugLogTree())
        Timber.plant(LogFileWriterTree(this))
        CrashReporter.install()
        toolsInitializer.initTools()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(setLocale(base))
        MultiDex.install(this)
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        setLocale(this)
    }

    companion object {
        const val TAG = "MainApplication"
        lateinit var application: MainApplication

        @JvmStatic
        val loginStateStream = LoginStateStream()

        @JvmStatic
        fun instance(): MainApplication {
            return application
        }
    }

    var isIsAppForeground = false

    private fun onAppBackgrounded() {
        LocalLog.info(TAG, "App in background")
        preferences.isForegroundApp = false
        isIsAppForeground = false
    }

    private fun onAppForegrounded() {
        LocalLog.info(TAG, "App in foreground")
        preferences.isForegroundApp = true
        isIsAppForeground = true
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {
                onAppBackgrounded()
            }

            Lifecycle.Event.ON_RESUME -> {
                onAppForegrounded()
            }
            else -> {

            }
        }
    }
}