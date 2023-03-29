package com.harsh.taskhuman.ui.discover.repository

import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
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

    override suspend fun addFavorites(): Result<String> {
        return remote.addFavorites()
    }

    override suspend fun removeFavorites(): Result<String> {
        return remote.removeFavorites()
    }
}