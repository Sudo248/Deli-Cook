package com.sudo248.shopping_food.domain.model

data class BaseResponse<T>(
    val success: Boolean,
    val error: String? = null,
    val data: T
)
