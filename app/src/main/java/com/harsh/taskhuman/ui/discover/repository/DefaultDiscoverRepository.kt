package com.harsh.taskhuman.ui.discover.repository

import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.ui.discover.model.AddFavoriteResponse
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.model.RemoveFavoriteResponse
import com.harsh.taskhuman.ui.discover.source.DiscoverDataSource

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

class DefaultDiscoverRepository constructor(
    private val local: DiscoverDataSource,
    private val remote: DiscoverDataSource,
) : DiscoverRepository {
    override suspend fun getExploreTaskHuman(): Result<DiscoverResponse> {
        return remote.getExploreTaskHuman()
    }

    override suspend fun addFavorites(
        skillName: String,
        dictionaryName: String
    ): Result<AddFavoriteResponse> {
        return remote.addFavorites(skillName, dictionaryName)
    }

    override suspend fun removeFavorites(skillName: String): Result<RemoveFavoriteResponse> {
        return remote.removeFavorites(skillName)
    }
}