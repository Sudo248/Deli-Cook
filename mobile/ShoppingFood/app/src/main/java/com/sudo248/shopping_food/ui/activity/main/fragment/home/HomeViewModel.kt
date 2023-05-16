package com.sudo248.shopping_food.ui.activity.main.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.model.Food
import com.sudo248.shopping_food.domain.model.FoodStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<NavDirections>() {

    private val allFoods = mutableListOf<Food>()

    var currentStyle: FoodStyle = FoodStyle.FRANCE

    private val _foods = MutableLiveData<List<Food>>()
    val food: LiveData<List<Food>> = _foods

    var error: SingleEvent<String>? = null

    private var jobSearch: Job? = null

    init {
        getAllFood()
    }

    fun getAllFood() = launch(ioDispatcher) {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.getAllFood())
        if (response.isSuccess) {
            allFoods.clear()
            allFoods.addAll(response.get().data)
            getListFoodByCurrentStyle()
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(response.get().error ?: response.error().message)
            emitState(UiState.ERROR)
        }
    }

    fun getListFoodByStyle(style: FoodStyle) {
        currentStyle = style
        getListFoodByCurrentStyle()
    }

    fun getListFoodByCurrentStyle() {
        _foods.postValue(allFoods.filter { food ->
            food.style == currentStyle.value
        })
    }

    fun searchFoodByName(name: String) {
        jobSearch?.cancel()
        jobSearch = launch {
            delay(500)
            _foods.postValue(
                allFoods.filter { food ->
                    food.name.lowercase().contains(name)
                }
            )
        }
    }

    fun onFoodClick(food: Food) {
        navigator.navigateTo(HomeFragmentDirections.actionHomeFragmentToFoodDetailFragment(food))
    }

}