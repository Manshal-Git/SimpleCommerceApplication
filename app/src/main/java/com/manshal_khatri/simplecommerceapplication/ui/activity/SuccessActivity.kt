package com.manshal_khatri.simplecommerceapplication.ui.activity

import android.content.res.Resources
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.manshal_khatri.simplecommerceapplication.databinding.ActivitySuccessBinding
import com.manshal_khatri.simplecommerceapplication.util.Functions
import kotlinx.coroutines.*

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySuccessBinding
    lateinit var res : Resources

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res = this.resources

        with(binding) {
            btnToHome.setOnClickListener { finish() }

            CoroutineScope(Dispatchers.IO).launch {
                delay(2500)
                withContext(Dispatchers.Main) {
                    binding.animSuccess.apply {
                        visibility = VISIBLE
                        playAnimation()
                    }
                }
                delay(3000)
                withContext(Dispatchers.Main) {
                    with(Functions){
                        makeViewGone(progressLayout)
                        makeViewVisible(resultLayout)
                    }
                }
            }
        }
    }

    override fun onBackPressed(){}
}