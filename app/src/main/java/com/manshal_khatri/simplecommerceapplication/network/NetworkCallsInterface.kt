package com.manshal_khatri.simplecommerceapplication.network

import com.manshal_khatri.simplecommerceapplication.model.server_response.ResponseProducts
import com.manshal_khatri.simplecommerceapplication.model.server_response.ResponseStoreDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCallsInterface {

    @GET("sheets/8fd0f5c8-20ba-4c46-9adc-3179dcc8c8e1")
    fun fetchStoreDetails() : Call<ArrayList<ResponseStoreDetails>>

    @GET("AKfycbx87dOKieG7Adnis9AOzdU-tHu1YiDLKzulual33E8HTwc4Gwtx4cwMhRt84EHHjhsf/exec")
    fun fetchProducts() : Call<ResponseProducts>

    @GET("AKfycbx87dOKieG7Adnis9AOzdU-tHu1YiDLKzulual33E8HTwc4Gwtx4cwMhRt84EHHjhsf/exec")
    fun fetchProductById(@Query("id") id : Int) : Call<ResponseProducts>
}
