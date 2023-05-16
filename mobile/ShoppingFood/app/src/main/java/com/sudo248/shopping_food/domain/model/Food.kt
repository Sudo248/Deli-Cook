package com.sudo248.shopping_food.domain.model

import com.sudo248.base_android.base.ItemDiff

data class Food(
    val foodId: String,
    val name: String,
    val image: String,
    val description: String,
    val style: String,
    val rate: Double,
    val steps: List<String>,
    val types: List<FoodType>,
) : ItemDiff, java.io.Serializable {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return (other as Food).foodId == foodId
    }

}