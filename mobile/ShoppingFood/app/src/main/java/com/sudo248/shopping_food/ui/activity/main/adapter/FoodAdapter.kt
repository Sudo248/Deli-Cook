package com.sudo248.shopping_food.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.shopping_food.databinding.ItemHomeFoodBinding
import com.sudo248.shopping_food.domain.model.Food
import com.sudo248.shopping_food.ui.utils.Utils
import com.sudo248.shopping_food.ui.utils.format
import com.sudo248.shopping_food.ui.utils.loadImage

class FoodAdapter(
    private val onItemClick: (Food) -> Unit
) : BaseListAdapter<Food, FoodAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemHomeFoodBinding) : BaseViewHolder<Food, ItemHomeFoodBinding>(binding) {
        override fun onBind(item: Food) {
            binding.apply {
                txtStar.text = item.rate.format()
                loadImage(imgFood, item.image)
                txtNameFood.text = item.name
                lnLearn.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}