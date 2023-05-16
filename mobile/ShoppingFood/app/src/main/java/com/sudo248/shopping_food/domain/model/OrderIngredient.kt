package com.sudo248.shopping_food.domain.model

import com.sudo248.base_android.base.ItemDiff

data class OrderIngredient(
    var amount: Int,
    val ingredient: Ingredient
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return (other is OrderIngredient) && other.amount == amount && ingredient.isContentTheSame(other.ingredient)
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return (other is OrderIngredient) && other.amount == amount && ingredient.isItemTheSame(other.ingredient)
    }

}