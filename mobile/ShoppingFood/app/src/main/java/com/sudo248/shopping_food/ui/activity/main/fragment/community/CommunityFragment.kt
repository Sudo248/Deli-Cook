package com.sudo248.shopping_food.ui.activity.main.fragment.community

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.shopping_food.databinding.FragmentCommunityBinding
import com.sudo248.shopping_food.ui.activity.main.adapter.CommunityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding, CommunityViewModel>() {

    override val viewModel: CommunityViewModel by viewModels()

    override val enableStateScreen: Boolean
        get() = true

    private val adapter: CommunityAdapter by lazy {
        CommunityAdapter(viewModel)
    }

    override fun initView() {
        binding.rcvCommunity.adapter = adapter
        binding.refresh.setOnRefreshListener {
            viewModel.getCommunity()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.communities.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = false
            adapter.submitList(it)
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