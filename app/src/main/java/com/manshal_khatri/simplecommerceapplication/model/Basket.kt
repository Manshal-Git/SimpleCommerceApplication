package com.manshal_khatri.simplecommerceapplication.model

import androidx.room.Entity
import java.io.Serializable

@Entity
data class Basket(
    val itemIds : List<Int>
): Serializable
