package com.harsh.taskhuman.ui.discover.source.local

import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.libraries.preferences.Preferences
import com.harsh.taskhuman.ui.discover.model.AddFavoriteResponse
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.model.RemoveFavoriteResponse
import com.harsh.taskhuman.ui.discover.source.DiscoverDataSource

/**
 * Created by Harsh Mittal on 07/08/22.
 **/


class DiscoverLocalDataSource(
    private val preferences: Preferences
) : DiscoverDataSource {
    override suspend fun getExploreTaskHuman(): Result<DiscoverResponse> {
        TODO("No implementation needed!")
    }

    override suspend fun addFavorites(
        skillName: String,
        dictionaryName: String
    ): Result<AddFavoriteResponse> {
        TODO("No implementation needed!")
    }

    override suspend fun removeFavorites(skillName: String): Result<RemoveFavoriteResponse> {
        TODO("No implementation needed!")
    }
}