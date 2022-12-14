package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id : Int,
    val name : String,
    val price : Float,
    val imageUrl : String
)
