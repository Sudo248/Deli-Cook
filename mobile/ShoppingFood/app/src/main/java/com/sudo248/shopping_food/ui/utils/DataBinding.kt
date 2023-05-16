package com.sudo248.shopping_food.ui.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sudo248.shopping_food.R
import com.sudo248.shopping_food.domain.Constant

@BindingAdapter("imageUrl")
fun loadImage(image: ImageView, url: String) {
    if (url.isEmpty()) return
    var imageUrl = url
    if (!imageUrl.startsWith("http")) imageUrl = "${Constant.baseUrl}image/$url"
    Glide
        .with(image.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_error)
        .into(image)
}

@BindingAdapter("imageUri")
fun loadImage(image: ImageView, uri: Uri) {
    Glide
        .with(image.context)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_error)
        .into(image)
}

fun ImageView.loadImageFrom(url: String) {
    loadImage(this, url)
}