package com.harsh.taskhuman.ui.discover.source.dto

import com.google.gson.annotations.SerializedName

data class AddFavoriteRequestBody(
    @SerializedName("skillName") val skillName: String,
    @SerializedName("dictionaryName") val dictionaryName: String
)
