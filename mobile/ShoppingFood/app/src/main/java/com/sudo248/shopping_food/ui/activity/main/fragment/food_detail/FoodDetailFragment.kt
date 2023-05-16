package com.sudo248.shopping_food.ui.activity.main.fragment.food_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.invisible
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.shopping_food.databinding.FragmentFoodDetailBinding
import com.sudo248.shopping_food.domain.model.Food
import com.sudo248.shopping_food.domain.model.FoodType
import com.sudo248.shopping_food.ui.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding, FoodDetailViewModel>() {
    override val viewModel: FoodDetailViewModel by viewModels()
    private val args: FoodDetailFragmentArgs by navArgs()

    override val enableStateScreen: Boolean
        get() = true

    private var currentTypeIndex = 0

    override fun initView() {
        setupUi()
        setupFood(food = args.food)
    }

    private fun setupUi() {
        binding.txtBuyIngredient.setOnClickListener {
            viewModel.createOrder(food = args.food, currentTypeIndex)
        }

        binding.txtReadMore.setOnClickListener {
            navigateTo(FoodDetailFragmentDirections.actionFoodDetailFragmentToStepFragment(args.food))
        }

        binding.txtDescription.setOnClickListener {
            navigateTo(FoodDetailFragmentDirections.actionFoodDetailFragmentToStepFragment(args.food))
        }

        binding.imgBack.setOnClickListener {
            back()
        }
    }

    private fun setupFood(food: Food) {
        binding.apply {
            loadImage(imgFood, food.image)
            txtNameFood.text = food.name
            txtFoodStyle.text = "${food.style} Style"
            txtDescription.text = food.description
            txtChoiceType.text = "Choice of ${food.name}"
            setupTypeFood(food.types)
        }
    }

    private fun setupTypeFood(types: List<FoodType>) {
        when (types.size) {
            1 -> {
                binding.txtType2.invisible()
                binding.txtType3.invisible()
                binding.txtType1.text = types.first().name
            }
            2 -> {
                binding.txtType3.invisible()
                binding.txtType2.text = types[1].name
                binding.txtType1.text = types[0].name
            }
            else -> {
                binding.txtType3.text = types[2].name
                binding.txtType2.text = types[1].name
                binding.txtType1.text = types[0].name
            }
        }

        binding.txtType1.setOnClickListener {
            currentTypeIndex = 0
        }

        binding.txtType2.setOnClickListener {
            currentTypeIndex = 1
        }

        binding.txtType3.setOnClickListener {
            currentTypeIndex = 2
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