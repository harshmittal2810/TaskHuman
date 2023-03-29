package com.harsh.taskhuman.ui.discover.source.remote.service

import com.harsh.taskhuman.common.util.API
import com.harsh.taskhuman.ui.discover.model.AddFavoriteResponse
import com.harsh.taskhuman.ui.discover.model.DiscoverResponse
import com.harsh.taskhuman.ui.discover.model.RemoveFavoriteResponse
import com.harsh.taskhuman.ui.discover.source.dto.AddFavoriteRequestBody
import com.harsh.taskhuman.ui.discover.source.dto.RemoveFavoriteRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Harsh Mittal on 07/08/22.
 **/

interface DiscoverApiService {

    @GET(API.API_EXPLORE_TASK_HUMAN)
    suspend fun getExploreTaskHuman(): DiscoverResponse

    @POST(API.API_ADD_FAVORITES)
    suspend fun addFavorites(@Body addFavoriteRequestBody: AddFavoriteRequestBody): AddFavoriteResponse

    @POST(API.API_REMOVE_FAVORITES)
    suspend fun removeFavorites(@Body removeFavoriteRequestBody: RemoveFavoriteRequestBody): RemoveFavoriteResponse

}