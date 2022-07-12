package com.khalifa.foodapp.domain.usecase

import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetFoodDetailsUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(foodId: String): FoodDomainEntities {
        return foodRepository.getFoodDetails(foodId)
    }
}