package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoreDetail(
    @PrimaryKey
    val storeName : String,
    val owner : String,
    val address : String
)
