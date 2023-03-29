package com.harsh.taskhuman.common.util

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.harsh.taskhuman.R

fun ImageView.loadUserProfile(
    url: String? = null, roundedCornerRadius: Int? = 1
) {
    this.loadUrl(
        url, placeholder = R.drawable.default_profile_pic, roundedCornerRadius = roundedCornerRadius
    )
}

fun ImageView.loadUrl(
    url: String? = null, placeholder: Int? = null, roundedCornerRadius: Int? = 1
) {
    val requestOptions = if (url is String && url.contains(".gif")) {
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .override(this.width, this.height)
            .placeholder(placeholder ?: R.drawable.card_placeholder)
            .error(placeholder ?: R.drawable.card_placeholder)


    } else {
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .override(this.width, this.height)
            .transform(RoundedCorners(roundedCornerRadius ?: 1))
            .placeholder(placeholder ?: R.drawable.card_placeholder)
            .error(placeholder ?: R.drawable.card_placeholder)

    }

    if (url is String && url.startsWith("file://")) {
        Glide.with(this.context).asDrawable().load(Uri.parse(url)).apply(requestOptions).fitCenter()
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
//                    setImageDrawable(
//                        placeholder ?: ContextCompat.getDrawable(
//                            this@loadUrl.context, R.drawable.card_placeholder
//                        )
//                    )
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    setImageDrawable(
                        errorDrawable ?: ContextCompat.getDrawable(
                            this@loadUrl.context, R.drawable.card_placeholder
                        )
                    )
                }

                override fun onResourceReady(
                    resource: Drawable, transition: Transition<in Drawable>?
                ) {
                    setImageDrawable(resource)
                    if (resource is Animatable) {
                        val drawable = resource as Animatable
                        drawable.start()
                    }
                }

            })
    } else {
        Glide.with(this.context).asDrawable().load(url).apply(requestOptions).fitCenter()
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
//                    setImageDrawable(
//                        placeholder ?: ContextCompat.getDrawable(
//                            this@loadUrl.context, R.drawable.card_placeholder
//                        )
//                    )
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    setImageDrawable(
                        errorDrawable ?: ContextCompat.getDrawable(
                            this@loadUrl.context, R.drawable.card_placeholder
                        )
                    )
                }

                override fun onResourceReady(
                    resource: Drawable, transition: Transition<in Drawable>?
                ) {
                    setImageDrawable(resource)
                    if (resource is Animatable) {
                        val drawable = resource as Animatable
                        drawable.start()
                    }
                }

            })
    }
}

fun ImageView.loadUrlForGroup(
    url: String? = null, placeholder: Int? = null, roundedCornerRadius: Int? = 1
) {
    val requestOptions = if (url is String && url.contains(".gif")) {
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .override(this.width, this.height)
            .placeholder(placeholder ?: R.drawable.card_placeholder)
            .error(placeholder ?: R.drawable.card_placeholder).override(40, 40)


    } else {
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .override(this.width, this.height)
            .transform(RoundedCorners(roundedCornerRadius ?: 1))
            .placeholder(placeholder ?: R.drawable.card_placeholder)
            .error(placeholder ?: R.drawable.card_placeholder).override(40, 40)

    }

    if (url is String && url.startsWith("file://")) {
        Glide.with(this.context).asDrawable().load(Uri.parse(url)).apply(requestOptions).fitCenter()
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
//                    setImageDrawable(
//                        placeholder ?: ContextCompat.getDrawable(
//                            this@loadUrlForGroup.context, R.drawable.card_placeholder
//                        )
//                    )
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    setImageDrawable(
                        errorDrawable ?: ContextCompat.getDrawable(
                            this@loadUrlForGroup.context, R.drawable.card_placeholder
                        )
                    )
                }

                override fun onResourceReady(
                    resource: Drawable, transition: Transition<in Drawable>?
                ) {
                    setImageDrawable(resource)
                    if (resource is Animatable) {
                        val drawable = resource as Animatable
                        drawable.start()
                    }
                }

            })
    } else {
        Glide.with(this.context).asDrawable().load(url).apply(requestOptions).fitCenter()
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
//                    setImageDrawable(
//                        placeholder ?: ContextCompat.getDrawable(
//                            this@loadUrlForGroup.context, R.drawable.card_placeholder
//                        )
//                    )
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    setImageDrawable(
                        errorDrawable ?: ContextCompat.getDrawable(
                            this@loadUrlForGroup.context, R.drawable.card_placeholder
                        )
                    )
                }

                override fun onResourceReady(
                    resource: Drawable, transition: Transition<in Drawable>?
                ) {
                    setImageDrawable(resource)
                    if (resource is Animatable) {
                        val drawable = resource as Animatable
                        drawable.start()
                    }
                }

            })
    }
}