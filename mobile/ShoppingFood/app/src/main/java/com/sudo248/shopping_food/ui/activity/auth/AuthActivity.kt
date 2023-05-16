package com.sudo248.shopping_food.ui.activity.auth

import androidx.activity.viewModels
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.shopping_food.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    override val viewModel: AuthViewModel by viewModels()

    override val enableStateScreen: Boolean
        get() = true

    override fun initView() {

    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error?.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showDialog(
                    context = this@AuthActivity,
                    title = "Error",
                    description = message
                )
            }
        }
    }
}