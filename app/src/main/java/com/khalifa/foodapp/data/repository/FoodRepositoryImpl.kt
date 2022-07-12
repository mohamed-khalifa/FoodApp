package com.khalifa.foodapp.data.repository

import com.khalifa.foodapp.data.remote.datasource.FoodDetailsRemoteDataSourceImpl
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.domain.mapper.FoodDomainMapper
import com.khalifa.foodapp.domain.repository.FoodRepository
import javax.inject.Inject


class FoodRepositoryImpl @Inject constructor(
    private val foodDetailsRemoteDataSource: FoodDetailsRemoteDataSourceImpl,
    private val foodDomainMapper: FoodDomainMapper
) : FoodRepository {

    override suspend fun getFoodDetails(foodId: String): FoodDomainEntities =
        foodDomainMapper.apply(foodDetailsRemoteDataSource.getFoodDetails(foodId))
}

