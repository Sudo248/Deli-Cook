package com.sudo248.shopping_food.ui.activity.auth

import androidx.lifecycle.MutableLiveData
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.shopping_food.data.api.ApiService
import com.sudo248.shopping_food.domain.Constant
import com.sudo248.shopping_food.domain.model.BaseResponse
import com.sudo248.shopping_food.domain.model.ResetPassRequest
import com.sudo248.shopping_food.domain.model.UserRequest
import com.sudo248.shopping_food.ui.activity.intro.IntroActivity
import com.sudo248.shopping_food.ui.activity.main.MainActivity
import com.sudo248.shopping_food.ui.utils.getErrorMessageFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel<IntentDirections>() {

    val signUpSuccess = MutableLiveData(false)
    val resetPasswordSuccess = MutableLiveData(false)

    var error: SingleEvent<String>? = null

    fun signIn(request: UserRequest) = launch(ioDispatcher) {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.signIn(request), BaseResponse::class.java)
        if (response.isSuccess) {
            emitState(UiState.SUCCESS)
            SharedPreferenceUtils.put(Constant.TOKEN_KEY, response.get().data.token)
            if (SharedPreferenceUtils.getBoolean(Constant.IS_FIRST_OPEN_APP, true)) {
                navigator.navigateOff(IntroActivity::class.createActionIntentDirections())
            } else {
                navigator.navigateOff(MainActivity::class.createActionIntentDirections())
            }
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun signUp(request: UserRequest) = launch(ioDispatcher) {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.signUp(request))
        if (response.isSuccess) {
            emitState(UiState.SUCCESS)
            signUpSuccess.postValue(true)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

    fun resetPassword(request: ResetPassRequest) = launch(ioDispatcher) {
        emitState(UiState.LOADING)
        val response = handleResponse(apiService.resetPassword(request))
        if (response.isSuccess) {
            emitState(UiState.SUCCESS)
            resetPasswordSuccess.postValue(true)
        } else {
            error = SingleEvent(getErrorMessageFromResponse(response))
            emitState(UiState.ERROR)
        }
    }

}