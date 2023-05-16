package com.sudo248.shopping_food.domain.model

import java.util.Date

data class User(
    val userId: String,
    val email: String,
    val name: String,
    val avatar: String,
    val createAt: Date
)
