package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order (
    @PrimaryKey
    val productIds : List<Int>,
    val quantityList : List<Int>,
    val productPriceList : List<Float>
)