package com.sudo248.shopping_food.domain.model

data class Order(
    val orderId: String? = null,
    val totalPrice: Double = 0.0,
    val delivery: Double = 0.0,
    val promotion: Double = 0.0,
    val taxes: Double = 0.0,
    val ingredients: List<OrderIngredient>,
)