package com.harsh.taskhuman.common.constants

import android.app.Activity
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.harsh.taskhuman.R

class RemoteConfig(
    private val mContext: Activity,
    private var mRemoteConfigInterface: RemoteConfigInterface,
) {
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null

    init {
        initFirebaseConfig(mContext)
    }

    companion object {
        private var what_is_new_message: String = ""

        @JvmStatic
        fun getWhatIsNewMessage() = what_is_new_message
    }


    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return mFirebaseRemoteConfig!!
    }

    private fun initFirebaseConfig(context: Activity) {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings =
            FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build()
        mFirebaseRemoteConfig!!.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig!!.setDefaultsAsync(R.xml.remote_config_defaults)
        mFirebaseRemoteConfig!!.fetchAndActivate().addOnCompleteListener(context) { task ->
            if (task.isSuccessful) {
                mRemoteConfigInterface.onSuccess()
            } else {
                mRemoteConfigInterface.onSuccess()
                Log.e("Config ", "Config params not updated ")
            }
        }
    }

    interface RemoteConfigInterface {
        fun onSuccess()
    }
}