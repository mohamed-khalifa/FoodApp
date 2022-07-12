package com.khalifa.foodapp.domain.repository

import com.khalifa.foodapp.domain.entity.FoodDomainEntities

interface FoodRepository {
    suspend fun getFoodDetails(foodId: String): FoodDomainEntities
}