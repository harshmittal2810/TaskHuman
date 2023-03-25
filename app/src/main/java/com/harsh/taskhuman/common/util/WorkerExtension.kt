package com.harsh.taskhuman.common.util

import androidx.work.*
import androidx.work.ListenableWorker.Result
import com.harsh.taskhuman.common.util.WorkerConstants.MAX_RETRY_ATTEMPT
import com.harsh.taskhuman.libraries.logs.LocalLog
import org.threeten.bp.Duration
import java.util.concurrent.TimeUnit

fun WorkManager.enqueueUniquePeriodicWorkExtended(
    workerClass: Class<out ListenableWorker>,
    uniqueWorkName: String,
    repeatInterval: Duration,
    flexInterval: Duration? = null,
    tags: List<String> = emptyList(),
    existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.REPLACE,
    networkTypeConstraint: NetworkType = NetworkType.CONNECTED,
    requiresCharging: Boolean = false,
    isUserBound: Boolean = true
) {
    val constraints = Constraints.Builder().apply {
        setRequiredNetworkType(networkTypeConstraint)
        setRequiresCharging(requiresCharging)
    }.build()

    val periodicRequestBuilder =
        if (flexInterval == null) PeriodicWorkRequest.Builder(
            workerClass,
            repeatInterval.toMillis(),
            TimeUnit.MILLISECONDS
        )
        else PeriodicWorkRequest.Builder(
            workerClass,
            repeatInterval.toMillis(),
            TimeUnit.MILLISECONDS,
            flexInterval.toMillis(),
            TimeUnit.MILLISECONDS
        )

    periodicRequestBuilder.setConstraints(constraints)

    tags.forEach {
        periodicRequestBuilder.addTag(it)
    }

    if (isUserBound) {
        periodicRequestBuilder.addTag(WorkerConstants.USER_WORK_TAG)
    }

    enqueueUniquePeriodicWork(
        uniqueWorkName, existingPeriodicWorkPolicy, periodicRequestBuilder.build()
    )
}

/**
 * Extension function for enqueuing network constraint OneTimeRequest
 *
 * This is just initial implementation, it could be enhanced moving forward after finding out more requirements for work manager
 *
 * @param workClass worker class
 * @param uniqueWorkName
 * @param initialDelay
 * @param delayTimeUnit defaulted to milliseconds
 * @param tags tag list to give the option to tag work. The goal is to group related work together for example captain related
 * work so that we can maintain it easier, this way instead of stopping every captain work on logout from
 * service manager, we can simply stop all work by using the tag
 * @param existingWorkPolicy defaults to replace
 *
 */
fun WorkManager.enqueueUniqueOneTimeWorkExtended(
    workClass: Class<out ListenableWorker>,
    uniqueWorkName: String,
    initialDelay: Duration = Duration.ofSeconds(1),
    tags: List<String> = emptyList(),
    data: Data = Data.EMPTY,
    existingWorkPolicy: ExistingWorkPolicy = ExistingWorkPolicy.REPLACE,
    isUserBound: Boolean = true
) {
    val oneTimeRequestBuilder = OneTimeWorkRequest
        .Builder(workClass)
        .setConstraints(ConstraintsBuilder.requireNetwork())

    oneTimeRequestBuilder.setInitialDelay(initialDelay.toMillis(), TimeUnit.MILLISECONDS)

    tags.forEach {
        oneTimeRequestBuilder.addTag(it)
    }

    if (isUserBound) {
        oneTimeRequestBuilder.addTag(WorkerConstants.USER_WORK_TAG)
    }

    oneTimeRequestBuilder.setInputData(data)

    enqueueUniqueWork(uniqueWorkName, existingWorkPolicy, oneTimeRequestBuilder.build())
}

fun CoroutineWorker.retryWithLimit(limit: Int = MAX_RETRY_ATTEMPT): Result =
    if (this.runAttemptCount >= limit) {
        LocalLog.error(
            "CoroutineWorker",
            "${this.id} failed $MAX_RETRY_ATTEMPT times. Stopping worker..."
        )
        Result.failure()
    } else {
        LocalLog.error(
            "CoroutineWorker",
            "${this.id} failed ${this.runAttemptCount} times. Retrying..."
        )
        Result.retry()
    }

object ConstraintsBuilder {

    fun requireNetwork(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

    fun requireNetworkAndCharging(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
    }
}

object WorkerConstants {

    /**
     * Used to tag work are bound to the user.
     * All the work with this tag will be cancelled at signout
     */
    const val USER_WORK_TAG = "USER_WORK_TAG"
    const val MAX_RETRY_ATTEMPT = 3
}

