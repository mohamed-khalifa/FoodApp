package com.khalifa.foodapp.data.remote.datasource

import com.khalifa.foodapp.data.remote.entity.FoodDetailsResponse
import com.khalifa.foodapp.data.constant.NetworkStatus
import com.khalifa.foodapp.data.remote.service.ApiService
import com.khalifa.foodapp.data.util.safeApiCall

class FoodDetailsRemoteDataSourceImpl(private val apiService: ApiService) {
    suspend fun getFoodDetails(foodId: String): NetworkStatus<FoodDetailsResponse> =
        safeApiCall { apiService.getFoodDetails(foodId) }
}