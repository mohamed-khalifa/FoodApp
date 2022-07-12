package com.khalifa.foodapp.data.di

import com.khalifa.foodapp.data.remote.datasource.FoodDetailsRemoteDataSourceImpl
import com.khalifa.foodapp.data.remote.service.ApiService
import com.khalifa.foodapp.data.repository.FoodRepositoryImpl
import com.khalifa.foodapp.domain.mapper.FoodDomainMapper
import com.khalifa.foodapp.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideFoodDetailsRemoteDataSource(apiService: ApiService): FoodDetailsRemoteDataSourceImpl
        = FoodDetailsRemoteDataSourceImpl(apiService)


    @Provides
    fun provideFoodRepository(
        foodDetailsRemoteDataSourceImpl: FoodDetailsRemoteDataSourceImpl
    ): FoodRepository = FoodRepositoryImpl(foodDetailsRemoteDataSourceImpl, FoodDomainMapper())

}
