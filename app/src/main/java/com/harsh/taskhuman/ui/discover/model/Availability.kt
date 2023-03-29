package com.harsh.taskhuman.ui.discover.model

data class Availability(
    val color: String? = "",
    val endTime: Long? = -1,
    val startTime: Long? = -1,
    val status: Int? = -1
)