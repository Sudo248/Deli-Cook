package com.sudo248.shopping_food.domain.model

data class ResetPassRequest(
    val email:String,
    val newPassword: String,
)