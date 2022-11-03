package com.manshal_khatri.simplecommerceapplication.adapter.dataBinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("srcFromUrl")
fun AppCompatImageView.srcFromUrl(url : String?){
    url.let {
        Glide.with(this).load(url).into(this)
    }
}

@BindingAdapter("setTextByFloat")
fun AppCompatTextView.setTextByFloat(floatValue: Float){
    this.text = "$floatValue"
}

