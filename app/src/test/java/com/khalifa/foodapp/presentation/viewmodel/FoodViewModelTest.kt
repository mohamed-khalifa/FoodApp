package com.khalifa.foodapp.presentation.viewmodel

import com.khalifa.foodapp.data.constant.NetworkConstants.SOCKET_TIME_OUT_EXCEPTION
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.domain.usecase.GetFoodDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals

import org.junit.Test

class FoodViewModelTest : ViewModelTest() {
    private val getFoodDetailsUseCase: GetFoodDetailsUseCase = mockk(relaxed = true)
    private val viewModel = FoodViewModel(getFoodDetailsUseCase)
    private val foodId = "1"

    @Test
    fun `test uiState is notified with food detail when calling getFoodDetailsUseCase`() {
        val expected =
            FoodDomainEntities.FoodDomainEntity(
                "Ricotta cheese",
                "174",
                "3.04g",
                "11.26g",
                "12.98g",
                "0g",
                "0.27g",
                "8.29g",
                "4.01g"
            )
        runBlocking {
            coEvery { getFoodDetailsUseCase.invoke(foodId) } returns expected
            viewModel.getFoodDetails(foodId)
            assertEquals(expected, viewModel.uiState.value)
        }
    }

    @Test
    fun `test uiState is notified with error message when calling getFoodDetailsUseCase`() {
        val expected = FoodDomainEntities.Failure(SOCKET_TIME_OUT_EXCEPTION)
        runBlocking {
            coEvery { getFoodDetailsUseCase.invoke(any()) } returns expected
            viewModel.getFoodDetails(foodId)
            assertEquals(expected, viewModel.uiState.value)
        }
    }
}