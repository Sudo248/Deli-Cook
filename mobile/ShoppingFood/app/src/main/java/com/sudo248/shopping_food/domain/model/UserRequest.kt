package com.sudo248.shopping_food.domain.model

data class UserRequest(
    val email: String,
    val password: String,
    val name: String? = null,
    val avatar: String? = null,
)