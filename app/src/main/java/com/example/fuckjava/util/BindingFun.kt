package com.example.fuckjava.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

val options :RequestOptions = RequestOptions().placeholder(ColorDrawable(Color.BLACK))
    .fallback(ColorDrawable(Color.RED))

@SuppressLint("CheckResult")
@BindingAdapter("url","error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    options.error(error)
    Glide
        .with(view.context)
        .load(url)
        .apply(options)
        .into(view)
}