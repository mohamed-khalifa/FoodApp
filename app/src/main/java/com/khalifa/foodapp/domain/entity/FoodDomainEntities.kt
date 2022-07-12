package com.khalifa.foodapp.domain.entity

sealed class FoodDomainEntities {
    data class FoodDomainEntity(
        val title: String,
        val calories: String,
        val carbs: String,
        val protein: String,
        val fat: String,
        val fiber: String,
        val sugars: String,
        val saturatedFat: String,
        val unSaturatedFat: String
    ) : FoodDomainEntities()

    data class Failure(val errorText: String) : FoodDomainEntities()
}

