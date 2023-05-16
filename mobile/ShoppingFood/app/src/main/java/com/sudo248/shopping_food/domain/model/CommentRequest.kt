package com.sudo248.shopping_food.domain.model

data class CommentRequest(
    val foodId: String,
    val content: String
)