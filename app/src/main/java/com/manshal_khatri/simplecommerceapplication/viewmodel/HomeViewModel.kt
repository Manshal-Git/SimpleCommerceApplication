package com.manshal_khatri.simplecommerceapplication.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manshal_khatri.simplecommerceapplication.model.Product
import com.manshal_khatri.simplecommerceapplication.model.StoreDetail
import com.manshal_khatri.simplecommerceapplication.model.server_response.ResponseProducts
import com.manshal_khatri.simplecommerceapplication.model.server_response.ResponseStoreDetails
import com.manshal_khatri.simplecommerceapplication.repository.Repository
import com.manshal_khatri.simplecommerceapplication.util.LiveDataCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    lateinit var repository : Repository
    private val _storeDetails = MutableLiveData<StoreDetail>()
    val storeDetail : LiveData<StoreDetail> = _storeDetails
    private val _products = MutableLiveData<List<Product>>()
    val product : LiveData<List<Product>> get() = _products
    private val _basket = MutableLiveData<MutableList<Int>>(mutableListOf())
    val basket : LiveData<MutableList<Int>> get() = _basket
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun setupRepository(context : Context){
        repository = Repository(context)
    }

    fun getStoreDetails() {
        val response = repository.fetchStoreDetails()
        response.enqueue(object : Callback<ArrayList<ResponseStoreDetails>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseStoreDetails>>,
                response: Response<ArrayList<ResponseStoreDetails>>
            ) {
                println(response.body().toString())
                response.body()?.let {
                    val dataItem = it[0]
                    _storeDetails.value = StoreDetail(dataItem.store_name,dataItem.owner,dataItem.address)
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseStoreDetails>>, t: Throwable) {
                print(t.toString())
            }

        })

    }

    fun getProducts(){

        val response = repository.fetchProductList()
        response.enqueue(object : Callback<ResponseProducts> {
            override fun onResponse(call: Call<ResponseProducts>, response: Response<ResponseProducts>) {
                println(response.body().toString())
                response.body()?.let {
                    _products.value = it.data
                }
            }
            override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                print(t.toString())
            }
        })
    }

    fun addToBasket(productId : Int){
        _basket.value?.add(productId)
    }
    fun removeFromBasket(productId : Int){
        _basket.value?.remove(productId)
    }
    fun setLoadingStatus(loading : Boolean){
        _isLoading.value = loading
    }
    private fun refreshLiveData(code : LiveDataCode){
        when(code){
            LiveDataCode.StoreDetails -> _storeDetails.postValue(_storeDetails.value)
            else -> {}
        }
    }
}