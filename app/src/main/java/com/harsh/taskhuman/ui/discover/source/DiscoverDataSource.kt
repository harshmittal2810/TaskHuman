package com.harsh.taskhuman.ui.discover.source

import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

interface DiscoverDataSource {

    suspend fun getExploreTaskHuman(): Result<DiscoverResponse>

    suspend fun addFavorites(): Result<String>

    suspend fun removeFavorites(): Result<String>
}