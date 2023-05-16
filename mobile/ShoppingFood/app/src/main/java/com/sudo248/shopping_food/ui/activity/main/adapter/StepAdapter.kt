package com.sudo248.shopping_food.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.shopping_food.databinding.ItemStepBinding
import com.sudo248.shopping_food.ui.utils.loadImage

class StepAdapter : RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    private var steps = mutableListOf<String>()

    fun submitList(list: List<String>) {
        steps.clear()
        steps.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(url: String) {
            loadImage(binding.imgStep, url)
            binding.txtStep.text = "Step ${bindingAdapterPosition + 1}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStepBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(steps[position])
    }

    override fun getItemCount(): Int = steps.size
}