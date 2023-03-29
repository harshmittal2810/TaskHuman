package com.harsh.taskhuman.ui.discover.model

data class AddFavoriteResponse(
    val favorite: Favorite,
    val message: String,
    val showFavoriteToast: Boolean,
    val success: Boolean
)