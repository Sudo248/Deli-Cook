package com.sudo248.shopping_food.ui.activity.main.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel<NavDirections>() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    var error: SingleEvent<String>? = null

    init {
        getMe()
    }

    fun getMe() = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.getMe())
        if (response.isSuccess) {
            _user.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(response.get().error ?: response.error().message)
            emitState(UiState.ERROR)
        }
    }

}