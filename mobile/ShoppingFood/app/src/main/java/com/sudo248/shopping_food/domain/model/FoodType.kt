package com.sudo248.shopping_food.domain.model

data class FoodType(
    val image: String,
    val description: String,
    val name: String,
    val ingredients: List<Ingredient>
) : java.io.Serializable