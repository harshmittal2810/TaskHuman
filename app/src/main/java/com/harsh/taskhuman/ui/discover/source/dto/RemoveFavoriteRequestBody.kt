package com.harsh.taskhuman.ui.discover.source.dto

import com.google.gson.annotations.SerializedName

data class RemoveFavoriteRequestBody(
    @SerializedName("skillName") val skillName: String,
)
