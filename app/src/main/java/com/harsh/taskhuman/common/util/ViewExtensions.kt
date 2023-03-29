package com.harsh.taskhuman.common.util

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.ext.SdkExtensions.getExtensionVersion
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

/**
 * Extension function to prevent accidental multiple clicks.
 */
fun View?.preventDoubleClick(delayInMillis: Long = 1000L) {
    this?.apply {
        isEnabled = false
        postDelayed({ isEnabled = true }, delayInMillis)
    }
}

fun View.setOnSingleClickListener(
    clickListener: View.OnClickListener? = null, delayInMillis: Long = 1000L
) {
    clickListener?.let {
        setOnClickListener(OnSingleClickListener(it, delayInMillis))
    } ?: setOnClickListener(null)
}

fun View.hideKeyBoard() {
    try {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    } catch (e: Exception) {
        Log.e("KeyBoard", "Failed to hide the keyboard!", e)
    }
}

fun View.showKeyBoard(context: Context?, editText: EditText?) {
    if (context != null && editText != null) {
        val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.showSoftInput(editText, 0)
    }
}


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun RecyclerView.disableItemAnimator() {
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}


fun getPixels(dipValue: Int, context: Context): Int {
    val r: Resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dipValue.toFloat(), r.displayMetrics
    ).toInt()
}

inline fun safeExecute(
    logTag: String = "SafeExecute", logMessage: String = "", crossinline block: () -> Unit
) {
    try {
        block()
    } catch (e: Throwable) {
        Log.e(logTag, logMessage.ifBlank { "Failed to execute block!" }, e)
    }
}

private const val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2

object PhotoPickerAvailabilityChecker {

    fun isPhotoPickerAvailable(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> true
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                getExtensionVersion(Build.VERSION_CODES.R) >= ANDROID_R_REQUIRED_EXTENSION_VERSION
            }
            else -> false
        }
    }
}