package com.sudo248.shopping_food.ui.activity.main.fragment.home

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.base_android.utils.KeyBoardUtils
import com.sudo248.shopping_food.R
import com.sudo248.shopping_food.databinding.FragmentHomeBinding
import com.sudo248.shopping_food.domain.model.FoodStyle
import com.sudo248.shopping_food.ui.activity.main.adapter.FoodAdapter
import com.sudo248.shopping_food.ui.widget.VerticalTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override val enableStateScreen: Boolean
        get() = true

    private var lastCategorySelected: VerticalTextView? = null

    private val adapter: FoodAdapter by lazy {
        FoodAdapter {
            viewModel.onFoodClick(it)
        }
    }

    override fun initView() {
        setupCategory()
        setupSearch()
        binding.rcvFood.adapter = adapter
        binding.rcvFood.setHasFixedSize(true)
        binding.refresh.setOnRefreshListener {
            viewModel.getAllFood()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.food.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = false
            adapter.submitList(it)
        }
    }

    private fun setupSearch() {
        binding.edtSearch.addTextChangedListener {
            if (binding.edtSearch.isFocused) {
                viewModel.searchFoodByName(it.toString().lowercase())
            }
        }
        binding.edtSearch.setOnFocusChangeListener { _, isFocus ->
            if (isFocus) {
                binding.lnCategory.gone()
                viewModel.searchFoodByName(binding.edtSearch.text.toString())
            } else {
                binding.lnCategory.visible()
                viewModel.getListFoodByCurrentStyle()
            }
        }
    }

    private fun setupCategory() {
        lastCategorySelected = when (viewModel.currentStyle) {
            FoodStyle.FRANCE -> binding.txtCategoryFrance
            FoodStyle.VIETNAM -> binding.txtCategoryVietNam
            FoodStyle.AMERICA -> binding.txtCategoryAmerica
            else -> binding.txtCategoryIndia
        }
        lastCategorySelected?.setTextColor(requireContext().getColor(R.color.primaryColor))
        binding.apply {
            txtCategoryFrance.setOnClickListener {
                lastCategorySelected?.setTextColor(requireContext().getColor(R.color.white_EF_a50))
                txtCategoryFrance.setTextColor(requireContext().getColor(R.color.primaryColor))
                lastCategorySelected = txtCategoryFrance
                viewModel.getListFoodByStyle(FoodStyle.FRANCE)
            }

            txtCategoryVietNam.setOnClickListener {
                lastCategorySelected?.setTextColor(requireContext().getColor(R.color.white_EF_a50))
                txtCategoryVietNam.setTextColor(requireContext().getColor(R.color.primaryColor))
                lastCategorySelected = txtCategoryVietNam
                viewModel.getListFoodByStyle(FoodStyle.VIETNAM)
            }

            txtCategoryAmerica.setOnClickListener {
                lastCategorySelected?.setTextColor(requireContext().getColor(R.color.white_EF_a50))
                txtCategoryAmerica.setTextColor(requireContext().getColor(R.color.primaryColor))
                lastCategorySelected = txtCategoryAmerica
                viewModel.getListFoodByStyle(FoodStyle.AMERICA)
            }

            txtCategoryIndia.setOnClickListener {
                lastCategorySelected?.setTextColor(requireContext().getColor(R.color.white_EF_a50))
                txtCategoryIndia.setTextColor(requireContext().getColor(R.color.primaryColor))
                lastCategorySelected = txtCategoryIndia
                viewModel.getListFoodByStyle(FoodStyle.INDIA)
            }
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