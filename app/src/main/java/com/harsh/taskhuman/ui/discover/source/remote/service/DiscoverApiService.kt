package com.harsh.taskhuman.ui.discover.source.remote.service

import com.harsh.taskhuman.common.util.API
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

interface DiscoverApiService {

    @GET(API.API_EXPLORE_TASK_HUMAN)
    suspend fun getExploreTaskHuman(): DiscoverResponse

    @POST(API.API_ADD_FAVORITES)
    suspend fun addFavorites(): String

    @POST(API.API_REMOVE_FAVORITES)
    suspend fun removeFavorites(): String

}