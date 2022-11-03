package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity

@Entity
data class Product(
    val id : Int,
    val name : String,
    val price : Float,
    val imageUrl : String
)
