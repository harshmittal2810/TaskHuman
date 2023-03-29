package com.harsh.taskhuman.ui.discover.model

data class DiscoverResponse(
    val isNextPage: Boolean? = false,
    val message: String? = "",
    val skills: List<Skill>? = mutableListOf(),
    val success: Boolean? = false,
    val topicHeader: TopicHeader? = null
)