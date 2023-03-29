package com.harsh.taskhuman.ui.discover.repository

import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.ui.discover.model.AddFavoriteResponse
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.model.RemoveFavoriteResponse

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

interface DiscoverRepository {

    suspend fun getExploreTaskHuman(): Result<DiscoverResponse>

    suspend fun addFavorites(skillName: String, dictionaryName: String): Result<AddFavoriteResponse>

    suspend fun removeFavorites(skillName: String): Result<RemoveFavoriteResponse>

}