package com.manshal_khatri.simplecommerceapplication.model

data class BasketItem(
    val product: Product,
    var quantity : Int = 1,
    var totalPrice : Float
)
