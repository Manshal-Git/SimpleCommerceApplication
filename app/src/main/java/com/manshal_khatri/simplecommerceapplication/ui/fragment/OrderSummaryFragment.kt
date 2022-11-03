package com.manshal_khatri.simplecommerceapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.manshal_khatri.simplecommerceapplication.R
import com.manshal_khatri.simplecommerceapplication.adapter.recyclerView.BasketAdapter
import com.manshal_khatri.simplecommerceapplication.adapter.recyclerView.OrderAdapter
import com.manshal_khatri.simplecommerceapplication.databinding.FragmentOrderSummaryBinding
import com.manshal_khatri.simplecommerceapplication.model.Basket
import com.manshal_khatri.simplecommerceapplication.viewmodel.OrdersViewModel


class OrderSummaryFragment : Fragment() {

    private lateinit var binding : FragmentOrderSummaryBinding
    lateinit var mVM : OrdersViewModel
    lateinit var mBasket : Basket

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_summary, container, false)
        binding = FragmentOrderSummaryBinding.bind(view)

        if(mVM.basket.value?.isEmpty() == true){
            mBasket.let { mVM.setBasketItems(it.itemIds) }
        }

        with(binding){
            rvOrderItems.adapter = BasketAdapter(mVM)
            mVM.basket.observe(viewLifecycleOwner){
                rvOrderItems.adapter = OrderAdapter(mVM)
            }
            rvOrderItems.layoutManager = LinearLayoutManager(requireActivity())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(vm : OrdersViewModel, basket: Basket) =
            OrderSummaryFragment().apply {
                arguments = Bundle().apply {
                    mVM = vm
                    mBasket = basket
                }
            }
    }
}