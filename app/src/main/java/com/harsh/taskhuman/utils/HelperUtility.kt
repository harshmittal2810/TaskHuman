package com.harsh.taskhuman.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Environment
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.constants.StringConstants
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.common.util.NetworkConnectivity
import com.harsh.taskhuman.common.util.isNotValidOrEmpty
import com.harsh.taskhuman.libraries.logs.LocalLog
import java.io.File
import java.io.IOException


/**
 * Created by Harsh Mittal on 15/07/22.
 */
object HelperUtility {

    private const val TAG = "HelperUtility"
    private const val OVAL_TEXT_CORNER_RADIUS_20 = 20

    @JvmStatic
    fun internetCheck(_context: Context?): Boolean {
        return NetworkConnectivity.isConnectedToNetWork(_context)
    }

    fun getBaseUrl(prefHelper: PrefHelper): String {
        return if (prefHelper.envBaseUrl.isNotValidOrEmpty()) {
            StringConstants.NETWORK_BASE_URL
        } else {
            prefHelper.envBaseUrl
        }
    }

    fun getDialogOKCancel(
        activity: AppCompatActivity, message: String?,
        okListener: DialogInterface.OnClickListener?,
    ): AlertDialog {
        return AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton(activity.getString(R.string.ok), okListener)
            .setNegativeButton(activity.getString(R.string.cancel), null)
            .create()
    }

    fun fileAllowed(uri: String): Boolean {
        val file = File(uri)
        val fileSize = (file.length() / 1024).toString().toInt()
        if (fileSize < 10240) {
            return true
        }
        return false
    }

    fun createImageFile(context: Context): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File(storageDir, "empuls${System.currentTimeMillis()}.png")
        if (!image.exists()) {
            try {
                image.createNewFile()
            } catch (e: IOException) {
                LocalLog.error(TAG, e.message, e)
            }
        }
        return image
    }

    @JvmStatic
    fun convertDpToPixel(dp: Int, context: Context): Int {
        val resources: Resources = context.resources
        val metrics: DisplayMetrics = resources.displayMetrics
        return (dp * (metrics.densityDpi / 160f)).toInt()
    }
}