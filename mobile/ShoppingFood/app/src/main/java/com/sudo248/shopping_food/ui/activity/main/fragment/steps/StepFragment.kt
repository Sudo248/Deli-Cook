package com.sudo248.shopping_food.ui.activity.main.fragment.steps

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.shopping_food.databinding.FragmentStepBinding
import com.sudo248.shopping_food.domain.model.Food
import com.sudo248.shopping_food.ui.activity.main.adapter.StepAdapter
import com.sudo248.shopping_food.ui.activity.main.fragment.food_detail.FoodDetailFragmentArgs
import com.sudo248.shopping_food.ui.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StepFragment : BaseFragment<FragmentStepBinding, StepViewModel>() {

    override val viewModel: StepViewModel by viewModels()
    private val args: StepFragmentArgs by navArgs()

    private val adapter: StepAdapter by lazy {
        StepAdapter()
    }

    override fun initView() {
        setupFood(args.food)
    }

    private fun setupFood(food: Food) {
        binding.apply {
            loadImage(imgFood, food.image)
            txtType.text = food.types[0].name
            txtNameFood.text = food.name
            txtStyleFood.text = "${food.style} Style"
            rcvSteps.adapter = adapter
            adapter.submitList(food.steps)
            imgBack.setOnClickListener {
                back()
            }
        }
    }

}