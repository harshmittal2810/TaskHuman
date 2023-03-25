package com.harsh.taskhuman.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Harsh Mittal on 23/09/22.
 */
data class CommonResponse(
    var `data`: CommonModel? = null
) {

    fun isSuccess(): Boolean {
        return data?.model?.success == 1
    }

    fun getMessage(): String {
        return data?.model?.message ?: ""
    }

    data class CommonModel(
        @SerializedName("deletePost", alternate = ["deleteComment"])
        var model: CommonDataModel? = null
    )

    data class CommonDataModel(
        var success: Int? = 0,
        var message: String? = null
    )
}
