package com.sudo248.shopping_food.domain.model

import com.sudo248.base_android.base.ItemDiff

data class Community(
    val foodId: String,
    val image: String,
    val description: String,
    val rate: Double,
    val comments: MutableList<Comment>
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return foodId == (other as Community).foodId
    }

}
