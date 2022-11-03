package com.manshal_khatri.simplecommerceapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.manshal_khatri.simplecommerceapplication.R
import com.manshal_khatri.simplecommerceapplication.adapter.recyclerView.BasketAdapter
import com.manshal_khatri.simplecommerceapplication.databinding.FragmentUserBasketBinding
import com.manshal_khatri.simplecommerceapplication.model.Basket
import com.manshal_khatri.simplecommerceapplication.viewmodel.OrdersViewModel

class UserBasketFragment : Fragment() {

    private lateinit var binding : FragmentUserBasketBinding
    lateinit var mVM : OrdersViewModel
    lateinit var mBasket : Basket

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_basket, container, false)
        binding = FragmentUserBasketBinding.bind(view)

        if(mVM.basket.value?.isEmpty() == true){
            mBasket.let { mVM.setBasketItems(it.itemIds) }
        }

        with(binding){
            rvBasketItems.adapter = BasketAdapter(mVM)
            mVM.basket.observe(viewLifecycleOwner){
                rvBasketItems.adapter = BasketAdapter(mVM)
            }
            rvBasketItems.layoutManager = LinearLayoutManager(requireActivity())
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(vm : OrdersViewModel,basket: Basket) =
            UserBasketFragment().apply {
                arguments = Bundle().apply {
                   mVM = vm
                    mBasket = basket
                }
            }
    }
}