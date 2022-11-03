package com.manshal_khatri.simplecommerceapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderStatusViewModel : ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val success : LiveData<Boolean> = _isSuccess

    fun processOrder(){

    }
}