package com.sudo248.shopping_food.ui.activity.main.fragment.profile

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.shopping_food.BuildConfig
import com.sudo248.shopping_food.databinding.FragmentProfileBinding
import com.sudo248.shopping_food.ui.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override fun initView() {
        binding.txtVersion.text = "Version: ${BuildConfig.VERSION_NAME}"
        viewModel.user.observe(viewLifecycleOwner) {
            loadImage(binding.imgProfile, it.avatar)
            binding.txtEmail.text = it.email
            binding.txtName.text = it.name
        }
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error?.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showDialog(
                    context = requireContext(),
                    title = "Error",
                    description = message
                )
            }
        }
    }
}