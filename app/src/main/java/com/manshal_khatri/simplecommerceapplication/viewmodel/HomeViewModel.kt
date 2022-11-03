package com.manshal_khatri.simplecommerceapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manshal_khatri.simplecommerceapplication.model.Product
import com.manshal_khatri.simplecommerceapplication.model.StoreDetail
import com.manshal_khatri.simplecommerceapplication.ui.activity.productList

class HomeViewModel : ViewModel() {

    private val _storeDetails = MutableLiveData<StoreDetail>()
    val storeDetail : LiveData<StoreDetail> = _storeDetails
    private val _products = MutableLiveData<List<Product>>()
    val product : LiveData<List<Product>> get() = _products
    private val _basket = MutableLiveData<MutableList<Int>>(mutableListOf())
    val basket : LiveData<MutableList<Int>> get() = _basket

    suspend fun getStoreDetails() {
            _storeDetails.value = StoreDetail("Zyephr","Manshal Khatri","550,indiranagr-2,lambha,ahemdabad,Gujrat")
    }

    suspend fun getProducts(){
        for( i in 0..6){
            productList.add(Product(i,"Shampoo",(45*i).toFloat(),"https://m.media-amazon.com/images/I/51WcofHzdJS._SX679_.jpg"))
        }
        _products.value = productList
    }

    fun addToBasket(productId : Int){
        _basket.value?.add(productId)
    }
    fun removeFromBasket(productId : Int){
        _basket.value?.remove(productId)
    }
}