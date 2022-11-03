package com.manshal_khatri.simplecommerceapplication.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manshal_khatri.simplecommerceapplication.databinding.ItemOrderProductBinding
import com.manshal_khatri.simplecommerceapplication.util.Functions.setImage
import com.manshal_khatri.simplecommerceapplication.viewmodel.OrdersViewModel

class OrderAdapter(private val vm : OrdersViewModel) : RecyclerView.Adapter<OrderAdapter.ViewHolder>()  {

    private val basketItemsList = vm.basket.value!!
    var totalAmount = 0.0f

    inner class ViewHolder(binding : ItemOrderProductBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.tvName
        val price = binding.tvPrice
        val image = binding.ivProduct
        val quantity = binding.quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOrderProductBinding.inflate(
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
            setImage(product.imageUrl,holder.image)
            holder.name.text = product.name
            setTotalPrice("$totalPrice")
            setQty("$quantity")
            totalAmount+=totalPrice
        }
        vm.setTotalAmount(totalAmount)
    }

    override fun getItemCount() = basketItemsList.size
}