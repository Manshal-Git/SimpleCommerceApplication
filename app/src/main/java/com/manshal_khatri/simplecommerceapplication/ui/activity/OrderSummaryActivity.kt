package com.manshal_khatri.simplecommerceapplication.ui.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.manshal_khatri.simplecommerceapplication.R
import com.manshal_khatri.simplecommerceapplication.databinding.ActivityOrderSummuryBinding
import com.manshal_khatri.simplecommerceapplication.model.Basket
import com.manshal_khatri.simplecommerceapplication.ui.fragment.OrderSummaryFragment
import com.manshal_khatri.simplecommerceapplication.ui.fragment.UserBasketFragment
import com.manshal_khatri.simplecommerceapplication.util.Constants
import com.manshal_khatri.simplecommerceapplication.util.Functions
import com.manshal_khatri.simplecommerceapplication.util.NavAction
import com.manshal_khatri.simplecommerceapplication.viewmodel.OrdersViewModel

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOrderSummuryBinding
    lateinit var vm : OrdersViewModel
    lateinit var mBasket : Basket
    lateinit var res : Resources
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummuryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res = this.resources
        vm = ViewModelProvider(this)[OrdersViewModel::class.java]
        mBasket = intent.getSerializableExtra(Constants.KEY_BASKET) as Basket
        if(mBasket.itemIds.isNotEmpty()){
            replaceFragment(NavAction.MyBasketScreen)
            setupNavButton()

            // Observers
            vm.totalAmount.observe(this){
                binding.tvAmount.text = "₹ $it"
            }
            vm.basket.observe(this){
                Functions.makeViewGone(binding.noItemsLayout)
            }
        }else{
            onEmptyBasketSetup()
        }

    }

    fun replaceFragment(navMode: NavAction){
        val fragment = if(navMode==NavAction.MyBasketScreen) UserBasketFragment.newInstance(vm,mBasket)
        else OrderSummaryFragment.newInstance(vm,mBasket)
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(binding.fragmentContainerView.id,
                fragment).commit()
        setToolBarTitle(navMode)
    }

    private fun setToolBarTitle(navMode: NavAction){
        val stringId = when(navMode){
            NavAction.MyBasketScreen -> R.string.my_basket
            NavAction.OrderSummaryScreen -> R.string.order_summary
        }
        binding.basketToolbar.title = res.getString(stringId)
    }

    fun setupNavButton(){
        binding.btnOrder.apply {
            setText(R.string.place_order)
            setOnClickListener {
                binding.btnOrder.text = res.getString(R.string.confirm_order)
                replaceFragment(NavAction.OrderSummaryScreen)
                it.setOnClickListener {
                    startActivity(Intent(this@OrderSummaryActivity, SuccessActivity::class.java))
                    finish()
                }
            }
        }
    }

    fun onEmptyBasketSetup(){
        Functions.makeViewVisible(binding.noItemsLayout)
        binding.btnBackToProducts.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount==0) finish()
        else {
            setToolBarTitle(NavAction.MyBasketScreen)
            setupNavButton()
        }
    }
}