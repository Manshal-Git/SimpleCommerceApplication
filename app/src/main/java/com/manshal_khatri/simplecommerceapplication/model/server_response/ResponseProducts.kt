package com.manshal_khatri.simplecommerceapplication.model.server_response

import com.manshal_khatri.simplecommerceapplication.model.Product

data class ResponseProducts(
    val `data`: List<Product>
)