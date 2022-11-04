package com.manshal_khatri.simplecommerceapplication.repository

import android.content.Context
import com.manshal_khatri.simplecommerceapplication.network.NetworkService
import com.manshal_khatri.simplecommerceapplication.room.SimpleCommerceDB
import com.manshal_khatri.simplecommerceapplication.util.Constants

class Repository(context: Context) {
    private val remoteDataSourceGS = NetworkService.getRetrofitInstance(Constants.BASE_URL_GS)
    private val remoteDataSourceBS = NetworkService.getRetrofitInstance(Constants.BASE_URL_SB)
    private val localDataSource = SimpleCommerceDB.getDatabase(context)

    fun fetchStoreDetails() = remoteDataSourceBS.fetchStoreDetails()

    fun fetchProductList() = remoteDataSourceGS.fetchProducts()

    fun fetchProductById(id : Int) = remoteDataSourceGS.fetchProductById(id)
}