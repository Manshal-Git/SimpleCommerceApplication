package com.manshal_khatri.simplecommerceapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    fun getRetrofitInstance(baseUrl : String): NetworkCallsInterface {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit.create(NetworkCallsInterface::class.java)
    }

    fun checkForInternet(context : Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo?=connectivityManager.activeNetworkInfo
        return if(activeNetwork?.isConnected!=null){
            activeNetwork.isConnected
        }else{
            false
        }
    }
}