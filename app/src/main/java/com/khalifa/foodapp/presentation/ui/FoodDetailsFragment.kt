package com.khalifa.foodapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.khalifa.foodapp.R
import com.khalifa.foodapp.databinding.FragmentFoodDetailsBinding
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import com.khalifa.foodapp.presentation.util.SensorManagerUtil
import com.khalifa.foodapp.presentation.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {

    private var _binding: FragmentFoodDetailsBinding? = null

    private val binding get() = _binding!!
    private val viewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSensorEvent()
        viewModel.uiState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is FoodDomainEntities.FoodDomainEntity -> bindFoodData(result)
                is FoodDomainEntities.Failure -> showToast(result.errorText)
            }
        }
        showToast(resources.getString(R.string.shake_device_message))
    }

    override fun onResume() {
        super.onResume()
        SensorManagerUtil.registerEventListener()
    }


    private fun initializeSensorEvent() {
        context?.let {
            SensorManagerUtil.initializeSensorEvent(it) {
                viewModel.getFoodDetails(viewModel.randomFoodId())
            }
        }
    }

    private fun bindFoodData(food: FoodDomainEntities.FoodDomainEntity) {
        with(binding) {
            titleTextView.text = food.title
            caloriesTextView.text = food.calories
            carbsCountTextView.text = food.carbs
            proteinCountTextView.text = food.protein
            fatCountTextView.text = food.fat
            fiberCountTextView.text = food.fiber
            sugarsCountTextView.text = food.sugars
            saturatedFatCountTextView.text = food.saturatedFat
            unSaturatedFatCountTextView.text = food.unSaturatedFat
        }
    }


    private fun showToast(text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_LONG
        ).show()
    }


    override fun onPause() {
        SensorManagerUtil.unRegisterEventListener()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}