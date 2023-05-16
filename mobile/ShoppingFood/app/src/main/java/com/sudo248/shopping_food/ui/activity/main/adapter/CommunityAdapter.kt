package com.sudo248.shopping_food.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.base_android.ktx.visible
import com.sudo248.shopping_food.databinding.ItemCommunityBinding
import com.sudo248.shopping_food.domain.model.Comment
import com.sudo248.shopping_food.domain.model.CommentRequest
import com.sudo248.shopping_food.domain.model.Community
import com.sudo248.shopping_food.ui.activity.main.fragment.community.CommunityViewModel
import com.sudo248.shopping_food.ui.utils.loadImage

class CommunityAdapter(
    private val viewModel: CommunityViewModel
) : BaseListAdapter<Community, CommunityAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCommunityBinding) : BaseViewHolder<Community, ItemCommunityBinding>(binding) {

        private val adapter: CommentAdapter by lazy {
            CommentAdapter()
        }

        override fun onBind(item: Community) {

            binding.apply {
                loadImage(imgFood, item.image)
                rcvComment.adapter = adapter
                adapter.submitList(item.comments)
//                edtComment.setOnFocusChangeListener { _, isFocus ->
//                    imgSend.isVisible = isFocus
//                }
                imgSend.setOnClickListener {
                    viewModel.postComment(
                        CommentRequest(
                            item.foodId,
                            edtComment.text.toString()
                        )
                    ) {
                        item.comments.add(it)
                        adapter.submitList(item.comments)
                        edtComment.setText("")
                        edtComment.clearFocus()
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommunityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}