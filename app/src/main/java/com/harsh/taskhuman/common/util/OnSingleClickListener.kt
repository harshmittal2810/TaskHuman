package com.harsh.taskhuman.common.util

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

class OnSingleClickListener(
    private val clickListener: View.OnClickListener? = null,
    private val delayInMillis: Long = 1000
) : View.OnClickListener {

    private val canClick = AtomicBoolean(true)

    override fun onClick(view: View?) {

        if (canClick.getAndSet(false)) {
            view?.run {
                postDelayed({
                    canClick.set(true)
                }, delayInMillis)
                clickListener?.onClick(view)
            }
        }
    }
}