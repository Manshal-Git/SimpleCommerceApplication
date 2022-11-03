package com.manshal_khatri.simplecommerceapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manshal_khatri.simplecommerceapplication.adapter.recyclerView.ProductsAdapter
import com.manshal_khatri.simplecommerceapplication.databinding.ActivityHomeBinding
import com.manshal_khatri.simplecommerceapplication.model.Basket
import com.manshal_khatri.simplecommerceapplication.model.Product
import com.manshal_khatri.simplecommerceapplication.model.StoreDetail
import com.manshal_khatri.simplecommerceapplication.util.Constants
import com.manshal_khatri.simplecommerceapplication.util.Functions
import com.manshal_khatri.simplecommerceapplication.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val productList = mutableListOf<Product>()

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    lateinit var vm : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        with(binding){
            vm.storeDetail.observe(this@HomeActivity) {
                setupStoreDetails(it)
            }
            rvProducts.layoutManager = LinearLayoutManager(this@HomeActivity)
            ivMyBasket.setOnClickListener {
                vm.basket.value?.let {
                    startActivity(Intent(this@HomeActivity,OrderSummaryActivity::class.java).apply {
                            putExtra(Constants.KEY_BASKET,Basket(it))
                        }
                    )
                }
            }
        }
        vm.product.observe(this@HomeActivity){
            binding.rvProducts.adapter = ProductsAdapter(vm)
        }
        with(vm) {
            CoroutineScope(Dispatchers.Main).launch {
                getStoreDetails()
                getProducts()
            }
        }
    }
    private fun setupStoreDetails(storeDetails : StoreDetail){
        with(storeDetails){
            with(Functions){
                setUpTextView(binding.tvStoreName,storeName)
                setUpTextView(binding.tvOwner,owner)
                setUpTextView(binding.tvAddress,address)
            }
        }
    }
}