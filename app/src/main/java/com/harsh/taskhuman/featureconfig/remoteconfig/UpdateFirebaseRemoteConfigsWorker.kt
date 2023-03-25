package com.harsh.taskhuman.featureconfig.remoteconfig

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.harsh.taskhuman.featureconfig.FeatureConfigManager
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

/**
 * Created by Harsh Mittal on 15/07/22
 */

@HiltWorker
class UpdateFirebaseRemoteConfigsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val featureConfigManager: Lazy<FeatureConfigManager>,
    private val updateFirebaseRemoteTask: UpdateFirebaseRemoteTask
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            Timber.d("#### worker for fetching remote config")
            updateFirebaseRemoteTask.refreshToken(
                workerParameters.inputData.getBoolean(
                    ARG_FORCE_REFRESH,
                    false
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
            return@coroutineScope Result.retry()
        }

        Timber.d("#### worker for fetching remote config success")
        featureConfigManager.get().notifyObservable()
        Result.success()
    }

    companion object {
        const val DEFAULT_TIMEOUT_SECONDS: Long = 3 * 60 * 60
        const val ARG_FORCE_REFRESH = "ARG_FORCE_REFRESH"
        const val PERIODIC_WORKER_TAG = "UpdateFirebaseRemoteConfigs_periodic"
        const val ONETIME_WORKER_TAG = "UpdateFirebaseRemoteConfigs_onetime"
    }
}