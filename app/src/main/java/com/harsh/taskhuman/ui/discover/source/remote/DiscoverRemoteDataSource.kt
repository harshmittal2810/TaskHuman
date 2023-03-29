package com.harsh.taskhuman.ui.discover.source.remote

import android.content.Context
import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.data.source.RemoteDataSource
import com.harsh.taskhuman.ui.discover.model.AddFavoriteResponse
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.model.RemoveFavoriteResponse
import com.harsh.taskhuman.ui.discover.source.DiscoverDataSource
import com.harsh.taskhuman.ui.discover.source.dto.AddFavoriteRequestBody
import com.harsh.taskhuman.ui.discover.source.dto.RemoveFavoriteRequestBody
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

    override suspend fun addFavorites(
        skillName: String,
        dictionaryName: String
    ): Result<AddFavoriteResponse> {
        return getResult(applicationContext) {
            api.addFavorites(
                AddFavoriteRequestBody(
                    skillName,
                    dictionaryName
                )
            )
        }
    }

    override suspend fun removeFavorites(skillName: String): Result<RemoveFavoriteResponse> {
        return getResult(applicationContext) {
            api.removeFavorites(
                RemoveFavoriteRequestBody(
                    skillName
                )
            )
        }
    }
}