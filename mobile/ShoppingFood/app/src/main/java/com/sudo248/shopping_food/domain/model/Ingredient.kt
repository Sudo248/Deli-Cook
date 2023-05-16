package com.sudo248.shopping_food.domain.model

import com.sudo248.base_android.base.ItemDiff

data class Ingredient(
    val ingredientId: String,
    val name: String,
    val image: String,
    val from: String,
    val price: Double,
    var amount: Int,
) : ItemDiff, java.io.Serializable {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return (other as Ingredient).ingredientId == ingredientId
    }
}
