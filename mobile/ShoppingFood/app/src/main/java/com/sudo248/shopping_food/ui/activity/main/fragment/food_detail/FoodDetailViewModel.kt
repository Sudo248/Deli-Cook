package com.sudo248.shopping_food.ui.activity.main.fragment.food_detail

import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.model.Food
import com.sudo248.shopping_food.domain.model.Order
import com.sudo248.shopping_food.domain.model.OrderIngredient
import com.sudo248.shopping_food.ui.utils.getErrorMessageFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel<NavDirections>() {

    var error: SingleEvent<String>? = null

    fun createOrder(food: Food, currentTypeIndex: Int) = launch {
        emitState(UiState.LOADING)
        val order = Order(
            ingredients = food.types[currentTypeIndex].ingredients.map {
                OrderIngredient(
                    amount = it.amount,
                    ingredient = it
                )
            }
        )

        val response = handleResponse(apiService.postOrder(order))

        if (response.isSuccess) {
            emitState(UiState.SUCCESS)
            navigator.navigateTo(
                FoodDetailFragmentDirections.actionFoodDetailFragmentToShoppingListFragment(response.get().data.orderId!!)
            )
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }

    }

}