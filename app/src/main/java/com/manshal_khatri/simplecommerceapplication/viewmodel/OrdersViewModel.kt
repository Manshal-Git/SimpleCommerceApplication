package com.manshal_khatri.simplecommerceapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manshal_khatri.simplecommerceapplication.model.BasketItem
import com.manshal_khatri.simplecommerceapplication.ui.activity.productList

class OrdersViewModel : ViewModel() {
    private val _basket = MutableLiveData<MutableList<BasketItem>>(mutableListOf())
    val basket : LiveData<MutableList<BasketItem>> get() = _basket
    private val _totalAmount =  MutableLiveData<Float>(0f)
    val totalAmount :LiveData<Float> get() = _totalAmount

    fun setBasketItems(list : List<Int>){
        val productList = productList
        clearBasket()
        productList.forEach {
            if(list.contains(it.id)){
                _basket.value?.add(BasketItem(it, totalPrice = it.price))
            }
        }
        refreshBasket()
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