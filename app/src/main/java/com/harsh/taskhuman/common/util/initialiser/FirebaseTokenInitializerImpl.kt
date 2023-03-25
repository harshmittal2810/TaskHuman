package com.harsh.taskhuman.common.util.initialiser

import androidx.work.WorkManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.initialiser.login.LoginNotifier
import com.harsh.taskhuman.common.util.isNotValidOrEmpty
import com.harsh.taskhuman.libraries.logs.LocalLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */

@Singleton
class FirebaseTokenInitializerImpl @Inject constructor(
    private val loginNotifier: LoginNotifier,
    private val prefHelper: PrefHelper,
    private val workManager: WorkManager
) : FirebaseInitializer {

    override fun initializeToken() {
        GlobalScope.launch {
            loginNotifier.loginNotifierStream()
                .collect { isLoginNotified ->
                    if (isLoginNotified) {
                        LocalLog.debug(javaClass.simpleName, ">>>> Login Notifier $isLoginNotified")
                        onLoginSetup()
                    }
                    LocalLog.debug(
                        javaClass.simpleName,
                        " >>>> Login notifier with response $isLoginNotified"
                    )
                }
        }
    }

    private fun onLoginSetup() {
        LocalLog.debug(javaClass.simpleName, ">>>> Login Finished")
        var fcmId = prefHelper.gcmToken

        if (fcmId.isNotValidOrEmpty()) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    LocalLog.error(
                        javaClass.simpleName,
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                fcmId = task.result
                prefHelper.gcmToken = fcmId
                if (fcmId.isNotEmpty()) {
                    registerToken(fcmId)
                }
            })
        } else {
            registerToken(fcmId)
        }
        loginNotifier.notify(false)
    }

    private fun registerToken(fcmId: String) {
        LocalLog.debug("FCM_TOKEN", "Token ==> $fcmId")
        /*val builder = Data.Builder()
        builder.putString(SendFcmWorker.FCM_ID_KEY, fcmId)
        workManager.enqueueUniqueOneTimeWorkExtended(
            SendFcmWorker::class.java,
            SendFcmWorker.WORKER_UNIQUE_NAME,
            data = builder.build()
        )*/
    }
}