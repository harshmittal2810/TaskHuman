package com.harsh.taskhuman.ui.discover.model

data class BlogMetaData(
    val _id: String,
    val blogImageUrl: String,
    val estReadTime: Int,
    val link: String,
    val providerInfo: List<ProviderInfo>,
    val title: String
)