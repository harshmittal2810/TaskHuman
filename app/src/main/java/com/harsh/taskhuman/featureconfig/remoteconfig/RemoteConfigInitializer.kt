package com.harsh.taskhuman.featureconfig.remoteconfig

import androidx.work.*
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.ConstraintsBuilder
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Harsh Mittal on 15/07/22
 */

class RemoteConfigInitializer @Inject constructor(
    private val workManager: WorkManager,
    private val prefHelper: PrefHelper
) {

    fun init(firebaseRemoteConfig: FirebaseRemoteConfig) {
        schedulePeriodic()

        val isConfigStale = prefHelper.featureConfigStateRepo
        Timber.d("#### Is Config stale? $isConfigStale")
        if (firebaseRemoteConfig.info.lastFetchStatus == FirebaseRemoteConfig.LAST_FETCH_STATUS_NO_FETCH_YET || isConfigStale) {
            scheduleImmediateRefresh()
        }
    }

    private fun schedulePeriodic() {
        Timber.d("#### schedule periodic refresh")
        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            UpdateFirebaseRemoteConfigsWorker::class.java,
            UpdateFirebaseRemoteConfigsWorker.DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS,
            60, TimeUnit.SECONDS
        )
            .setConstraints(ConstraintsBuilder.requireNetwork())
            .setInputData(
                Data.Builder()
                    .putBoolean(UpdateFirebaseRemoteConfigsWorker.ARG_FORCE_REFRESH, false)
                    .build()
            )
            .build()

        workManager.enqueueUniquePeriodicWork(
            UpdateFirebaseRemoteConfigsWorker.PERIODIC_WORKER_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

    fun scheduleImmediateRefresh() {
        Timber.d("#### schedule immediate refresh")
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(UpdateFirebaseRemoteConfigsWorker::class.java)
                .setConstraints(ConstraintsBuilder.requireNetwork())
                .setInputData(
                    Data.Builder()
                        .putBoolean(UpdateFirebaseRemoteConfigsWorker.ARG_FORCE_REFRESH, true)
                        .build()
                )
                .build()

        workManager.enqueueUniqueWork(
            UpdateFirebaseRemoteConfigsWorker.ONETIME_WORKER_TAG,
            ExistingWorkPolicy.REPLACE,
            oneTimeWorkRequest
        )

    }

}