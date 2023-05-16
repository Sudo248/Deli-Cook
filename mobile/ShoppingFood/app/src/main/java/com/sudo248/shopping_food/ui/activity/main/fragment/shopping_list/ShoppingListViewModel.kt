package com.sudo248.shopping_food.ui.activity.main.fragment.shopping_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.model.Order
import com.sudo248.shopping_food.domain.model.OrderIngredient
import com.sudo248.shopping_food.ui.utils.getErrorMessageFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel<NavDirections>() {

    var error: SingleEvent<String>? = null

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order> = _order

    fun getOrder(orderId: String) = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.getOrder(orderId))
        if (response.isSuccess) {
            _order.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun getActiveOrder() = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.getActiveOrder())
        if (response.isSuccess) {
            _order.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun payOrder(orderId: String, onPaySuccess: () -> Unit) = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.payOrder(orderId))
        if (response.isSuccess) {
            emitState(UiState.SUCCESS)
            onPaySuccess.invoke()
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun updateOrder(orderId: String, orderIngredient: OrderIngredient) = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.putOrder(orderId, orderIngredient))
        if (response.isSuccess) {
            _order.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun deleteOrderIngredient(orderId: String, orderIngredient: OrderIngredient) = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.deleteIngredient(orderId, orderIngredient.ingredient.ingredientId))
        if (response.isSuccess) {
            _order.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

}