package com.sudo248.shopping_food.ui.activity.main.fragment.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.model.Comment
import com.sudo248.shopping_food.domain.model.CommentRequest
import com.sudo248.shopping_food.domain.model.Community
import com.sudo248.shopping_food.ui.utils.getErrorMessageFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel<NavDirections>() {

    private val _communities = MutableLiveData<List<Community>>()
    val communities: LiveData<List<Community>> = _communities

    var error: SingleEvent<String>? = null

    init {
        getCommunity()
    }

    fun getCommunity() = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.getCommunity())
        if (response.isSuccess) {
            _communities.postValue(response.get().data!!)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun postComment(comment: CommentRequest, onSuccess: (Comment) -> Unit) = launch {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.postComment(comment))
        if (response.isSuccess) {
            onSuccess.invoke(response.get().data)
            emitState(UiState.SUCCESS)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }
}