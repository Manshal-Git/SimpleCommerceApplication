package com.manshal_khatri.simplecommerceapplication.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

object Functions {
    fun setUpTextView(tv : TextView,text : String){
        tv.text = text
    }
    fun setImage(imgUrl : String,imageView : ImageView,){
        Glide.with(imageView.context).load(imgUrl).into(imageView)
    }
    fun makeViewVisible(v : View){
        v.visibility = VISIBLE
    }
    fun makeViewGone(v : View){
        v.visibility = GONE
    }
}