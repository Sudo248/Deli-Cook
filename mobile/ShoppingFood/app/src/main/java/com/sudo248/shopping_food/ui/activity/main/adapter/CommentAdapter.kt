package com.sudo248.shopping_food.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.shopping_food.databinding.ItemCommentBinding
import com.sudo248.shopping_food.domain.model.Comment
import com.sudo248.shopping_food.ui.utils.loadImage

class CommentAdapter : BaseListAdapter<Comment, CommentAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemCommentBinding) : BaseViewHolder<Comment, ItemCommentBinding>(binding) {
        override fun onBind(item: Comment) {
            binding.apply {
                loadImage(imgAvatarUser, item.user.avatar)
                txtNameUser.text = item.user.name
                txtComment.text = item.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}