package com.sudo248.shopping_food.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.apachat.swipereveallayout.core.ViewBinder
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.shopping_food.databinding.ItemShoppingBinding
import com.sudo248.shopping_food.domain.model.Ingredient
import com.sudo248.shopping_food.domain.model.OrderIngredient
import com.sudo248.shopping_food.ui.utils.Utils
import com.sudo248.shopping_food.ui.utils.loadImage

class OrderAdapter(
    private val onChangeAmount: (OrderIngredient) -> Unit,
    private val onDeleteItem: (OrderIngredient) -> Unit
) : BaseListAdapter<OrderIngredient, OrderAdapter.ViewHolder>() {

    private val viewBinder: ViewBinder = ViewBinder()

    inner class ViewHolder(binding: ItemShoppingBinding) : BaseViewHolder<OrderIngredient, ItemShoppingBinding>(binding) {
        override fun onBind(item: OrderIngredient) {
            viewBinder.bind(
                binding.swipeRevealLayout,
                "${item.ingredient.ingredientId}-${item.amount}"
            )
            binding.apply {
                loadImage(imgIngredient, item.ingredient.image)
                txtNameIngredient.text = item.ingredient.name
                txtAmount.text = item.amount.toString()
                txtFrom.text = item.ingredient.from
                txtPrice.text = Utils.formatCurrency(item.ingredient.price)

                imgMinus.setOnClickListener {
                    if (item.amount > 1) {
                        onChangeAmount.invoke(item.copy(amount = item.amount - 1))
                    }
                }

                imgAdd.setOnClickListener {
                    onChangeAmount.invoke(item.copy(amount = item.amount + 1))
                }

                lnDelete.setOnClickListener {
                    onDeleteItem.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemShoppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}