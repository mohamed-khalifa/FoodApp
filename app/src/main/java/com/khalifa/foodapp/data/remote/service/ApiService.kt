package com.khalifa.foodapp.data.remote.service

import com.khalifa.foodapp.data.remote.entity.FoodDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("foodipedia/codetest")
    suspend fun getFoodDetails(
        @Query("foodid") foodId: String,
    ): Response<FoodDetailsResponse>

}