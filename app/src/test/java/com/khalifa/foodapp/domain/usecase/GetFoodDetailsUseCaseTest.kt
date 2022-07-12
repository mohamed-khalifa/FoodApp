package com.khalifa.foodapp.domain.usecase

import com.khalifa.foodapp.data.repository.FoodRepositoryImpl
import com.khalifa.foodapp.domain.repository.FoodRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking

import org.junit.Test

class GetFoodDetailsUseCaseTest {

    private val foodRepository: FoodRepository = mockk(relaxed = true)
    private val getFoodDetailsUseCase = GetFoodDetailsUseCase(foodRepository)

    @Test
    fun `test repository getFoodDetails is called from getFoodDetailsUseCase`() {
        val foodId = "1"
        runBlocking {
            getFoodDetailsUseCase.invoke(foodId)
            coVerify(exactly = 1) { foodRepository.getFoodDetails(foodId) }
        }
    }
}