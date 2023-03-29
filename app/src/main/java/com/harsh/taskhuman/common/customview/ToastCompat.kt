package com.harsh.taskhuman.common.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.os.Build
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.BadTokenException
import android.widget.Toast
import androidx.annotation.StringRes

class ToastCompat(context: Context, private val baseToast: Toast) : Toast(context) {

    companion object {

        fun makeText(context: Context, text: CharSequence?, duration: Int): ToastCompat? {
            @SuppressLint("ShowToast") val toast = Toast.makeText(context, text, duration)
            toast?.let {
                setContextCompat(it.view, ToastContextWrapper(context))
                return ToastCompat(context, toast)
            } ?: run {
                return null
            }
        }

        @Throws(Resources.NotFoundException::class)
        fun makeText(context: Context, @StringRes resId: Int, duration: Int): ToastCompat? {
            return makeText(context, context.resources.getText(resId), duration)
        }

        private fun setContextCompat(view: View?, context: Context) {
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    val field = View::class.java.getDeclaredField("mContext")
                    field.isAccessible = true
                    field[view] = context
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }
            }
        }
    }

    override fun show() {
        baseToast.show()
    }

    override fun setDuration(duration: Int) {
        baseToast.duration = duration
    }

    override fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        baseToast.setGravity(gravity, xOffset, yOffset)
    }

    override fun setMargin(horizontalMargin: Float, verticalMargin: Float) {
        baseToast.setMargin(horizontalMargin, verticalMargin)
    }

    override fun setText(resId: Int) {
        baseToast.setText(resId)
    }

    override fun setText(s: CharSequence?) {
        baseToast.setText(s)
    }

    override fun setView(view: View) {
        baseToast.view = view
        setContextCompat(view, ToastContextWrapper(view.context))
    }

    override fun getHorizontalMargin(): Float {
        return baseToast.horizontalMargin
    }

    override fun getVerticalMargin(): Float {
        return baseToast.verticalMargin
    }

    override fun getDuration(): Int {
        return baseToast.duration
    }

    override fun getGravity(): Int {
        return baseToast.gravity
    }

    override fun getXOffset(): Int {
        return baseToast.xOffset
    }

    override fun getYOffset(): Int {
        return baseToast.yOffset
    }

    override fun getView(): View? {
        return baseToast.view
    }
}

internal class ToastContextWrapper(base: Context) : ContextWrapper(base) {
    override fun getApplicationContext(): Context {
        return ApplicationContextWrapper(baseContext.applicationContext)
    }

    private inner class ApplicationContextWrapper(base: Context) : ContextWrapper(base) {
        override fun getSystemService(name: String): Any {
            return if (Context.WINDOW_SERVICE == name) {
                // noinspection ConstantConditions
                WindowManagerWrapper((baseContext.getSystemService(name) as WindowManager))
            } else super.getSystemService(name)
        }
    }

    private inner class WindowManagerWrapper(private val base: WindowManager) : WindowManager {
        override fun getDefaultDisplay(): Display {
            return base.defaultDisplay
        }

        override fun removeViewImmediate(view: View) {
            base.removeViewImmediate(view)
        }

        override fun addView(view: View, params: ViewGroup.LayoutParams) {
            try {
                base.addView(view, params)
            } catch (e: BadTokenException) {
                // ignore
            }
        }

        override fun updateViewLayout(view: View, params: ViewGroup.LayoutParams) {
            base.updateViewLayout(view, params)
        }

        override fun removeView(view: View) {
            base.removeView(view)
        }
    }
}

