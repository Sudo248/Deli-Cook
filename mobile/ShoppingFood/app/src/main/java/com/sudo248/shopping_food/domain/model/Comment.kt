package com.sudo248.shopping_food.domain.model

import com.sudo248.base_android.base.ItemDiff

data class Comment(
    val user: User,
    val content: String
) : ItemDiff {
    override fun isContentTheSame(other: ItemDiff): Boolean {
        return this == other
    }

    override fun isItemTheSame(other: ItemDiff): Boolean {
        return user.userId == (other as Comment).user.userId && content == other.content
    }

}
