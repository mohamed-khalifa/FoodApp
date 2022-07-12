package com.khalifa.foodapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.domain.usecase.GetFoodDetailsUseCase
import com.khalifa.foodapp.presentation.constant.Constants.RANDOM_FOOD_ID_RANGE_END
import com.khalifa.foodapp.presentation.constant.Constants.RANDOM_FOOD_ID_RANGE_START
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodDetailsUseCase: GetFoodDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<FoodDomainEntities>()
    val uiState: LiveData<FoodDomainEntities> = _uiState

    fun getFoodDetails(foodId: String) {
        viewModelScope.launch {
            _uiState.value = getFoodDetailsUseCase.invoke(foodId)
        }
    }

    fun randomFoodId() = (RANDOM_FOOD_ID_RANGE_START..RANDOM_FOOD_ID_RANGE_END).random().toString()
}

