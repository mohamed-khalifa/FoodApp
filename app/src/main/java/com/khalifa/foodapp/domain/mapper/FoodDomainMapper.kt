package com.khalifa.foodapp.domain.mapper

import com.khalifa.foodapp.data.constant.NetworkConstants
import com.khalifa.foodapp.data.constant.NetworkStatus
import com.khalifa.foodapp.data.remote.entity.FoodDetailsResponse
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.presentation.constant.Constants.GRAM
import com.khalifa.foodapp.domain.util.formatDecimalNumberWithUnit

class FoodDomainMapper {
    fun apply(foodDetailsResponse: NetworkStatus<FoodDetailsResponse>): FoodDomainEntities =
        foodDetailsResponse.data?.response?.let { food ->
            FoodDomainEntities.FoodDomainEntity(
                food.title ?: "",
                (food.calories ?: 0.0).formatDecimalNumberWithUnit(),
                (food.carbs ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.protein ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.fat ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.fiber ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.sugar ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.saturatedFat ?: 0.0).formatDecimalNumberWithUnit(GRAM),
                (food.unSaturatedFat ?: 0.0).formatDecimalNumberWithUnit(GRAM)
            )
        } ?: run {
            FoodDomainEntities.Failure(
                foodDetailsResponse.errorMessage ?: NetworkConstants.GENERAL_ERROR
            )
        }
}


