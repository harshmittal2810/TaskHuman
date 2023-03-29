package com.harsh.taskhuman.ui.discover.model

data class Skill(
    val availability: Availability? = null,
    val blogMetaData: BlogMetaData? = null,
    val columns: Int? = -1,
    val dictionaryName: String? = "",
    val displayTileName: String? = "",
    val isFavorite: Boolean? = false,
    val moreProvidersAvailable: Boolean? = false,
    val providerInfo: List<ProviderInfoX>? = mutableListOf(),
    val skillIcon: String? = "",
    val tileBackground: String? = "",
    val tileColor: String? = "",
    val tileName: String? = "",
    val type: String? = ""
)