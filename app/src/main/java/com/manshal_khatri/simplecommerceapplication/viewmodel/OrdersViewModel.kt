package com.manshal_khatri.simplecommerceapplication.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manshal_khatri.simplecommerceapplication.model.BasketItem
import com.manshal_khatri.simplecommerceapplication.model.server_response.ResponseProducts
import com.manshal_khatri.simplecommerceapplication.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel : ViewModel() {

    lateinit var repository : Repository
    private val _basket = MutableLiveData<MutableList<BasketItem>>(mutableListOf())
    val basket : LiveData<MutableList<BasketItem>> get() = _basket
    private val _totalAmount =  MutableLiveData<Float>(0f)
    val totalAmount :LiveData<Float> get() = _totalAmount

    fun setupRepository(context : Context){
        repository = Repository(context)
    }

    fun setBasketItems(list : List<Int>){
        /*val productList = productList
        clearBasket()
        productList.forEach {
            if(list.contains(it.id)){
                _basket.value?.add(BasketItem(it, totalPrice = it.price))
            }
        }*/
        clearBasket()
        list.forEach { it ->
            val response = repository.fetchProductById(it)
            response.enqueue(object : Callback<ResponseProducts> {
                override fun onResponse(call: Call<ResponseProducts>, response: Response<ResponseProducts>) {

                    println(response.body().toString())
                    response.body()?.let {
                        val product = it.data[0]
                        _basket.value?.add(BasketItem(product, totalPrice = product.price))
                        refreshBasket()
                    }
                }

                override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                    print(t.toString())
                }

            })
        }
    }

    fun clearBasket() {
        _basket.value?.clear()
        refreshBasket()
    }

    private fun refreshBasket() {
        _basket.postValue(_basket.value)
    }

    private fun findItem(id:Int) = _basket.value?.find { it.product.id == id }

    fun reduceQty(id: Int) {
        findItem(id)?.let {  it.quantity = it.quantity-1 }
    }

    fun plusQty(id: Int) {
        findItem(id)?.let {  it.quantity = it.quantity+1 }
    }

    fun setTotalAmount(totalPrice: Float){
        _totalAmount.value = totalPrice
    }
}