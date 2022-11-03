package com.manshal_khatri.simplecommerceapplication.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manshal_khatri.simplecommerceapplication.databinding.ItemBasketProductBinding
import com.manshal_khatri.simplecommerceapplication.util.Functions
import com.manshal_khatri.simplecommerceapplication.viewmodel.OrdersViewModel

class BasketAdapter(private val vm : OrdersViewModel) : RecyclerView.Adapter<BasketAdapter.ViewHolder>()  {

    private val basketItemsList = vm.basket.value!!
    var totalAmount = 0.0f

    inner class ViewHolder(binding : ItemBasketProductBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.tvName
        val price = binding.tvPrice
        val image = binding.ivProduct
        val plusQty = binding.ivPlus
        val minusQty = binding.ivMinus
        val quantity = binding.quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBasketProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val basketItem = basketItemsList[position]
        fun setQty(qty:String){
            holder.quantity.text = qty
        }
        fun setTotalPrice(price : String){
            holder.price.text = price
        }

        with(basketItem){
            Functions.setImage(product.imageUrl, holder.image)
            holder.name.text = product.name
            setTotalPrice("$totalPrice")
            setQty("$quantity")
            totalAmount+=totalPrice
            holder.minusQty.setOnClickListener {
                vm.reduceQty(product.id)
                setQty("${quantity}")
                totalPrice = product.price*quantity
                setTotalPrice("${totalPrice}")
                vm.setTotalAmount(vm.totalAmount.value?.minus(product.price) ?: 0f)
            }
            holder.plusQty.setOnClickListener {
                vm.plusQty(product.id)
                setQty("${quantity}")
                totalPrice = product.price*quantity
                setTotalPrice("${totalPrice}")
                vm.setTotalAmount(vm.totalAmount.value?.plus(product.price) ?: 0f)
            }
        }
        vm.setTotalAmount(totalAmount)
    }

    override fun getItemCount() = basketItemsList.size

}