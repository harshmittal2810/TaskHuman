package com.harsh.taskhuman.ui.discover.source.remote

import android.content.Context
import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.data.source.RemoteDataSource
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.source.DiscoverDataSource
import com.harsh.taskhuman.ui.discover.source.remote.service.DiscoverApiService
import javax.inject.Inject

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

class DiscoverRemoteDataSource @Inject constructor(
    private val applicationContext: Context,
    private val api: DiscoverApiService,
) : DiscoverDataSource, RemoteDataSource {

    override suspend fun getExploreTaskHuman(): Result<DiscoverResponse> {
        return getResult(applicationContext) { api.getExploreTaskHuman() }
    }

    override suspend fun addFavorites(): Result<String> {
        return getResult(applicationContext) { api.addFavorites() }
    }

    override suspend fun removeFavorites(): Result<String> {
        return getResult(applicationContext) { api.removeFavorites() }
    }
}