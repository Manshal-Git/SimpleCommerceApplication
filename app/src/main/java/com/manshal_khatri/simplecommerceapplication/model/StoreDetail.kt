package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity

@Entity
data class StoreDetail(
    val storeName : String,
    val owner : String,
    val address : String
)
