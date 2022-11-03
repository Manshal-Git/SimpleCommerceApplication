package com.manshal_khatri.simplecommerceapplication.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manshal_khatri.simplecommerceapplication.R
import com.manshal_khatri.simplecommerceapplication.databinding.ItemProductBinding
import com.manshal_khatri.simplecommerceapplication.viewmodel.HomeViewModel

class ProductsAdapter(private val vm : HomeViewModel) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>()  {
    private val productList = vm.product.value!!

    inner class ViewHolder(binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.tvName
        val price = binding.tvPrice
        val image = binding.ivProduct
        val addToBasket = binding.btnAddToBasket
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        with(product){
            holder.name.text = name
            holder.price.text = "₹ $price"
            Glide.with(holder.itemView).load(imageUrl).into(holder.image)
            holder.addToBasket.setOnClickListener {

                    if(vm.basket.value!!.contains(id)){
                        setButtonState(it as ViewGroup,R.drawable.button_background,R.string.add_to_basket,R.color.white,R.color.white)
                        vm.removeFromBasket(id)
                    }else{
                        setButtonState(it as ViewGroup,R.drawable.edittext_background,R.string.added,R.color.black,R.color.black)
                        vm.addToBasket(id)
                    }
            }
        }
    }

    override fun getItemCount() = productList.size

    private fun setButtonState(v:ViewGroup, backgroundId :Int, stringResId:Int, textColorId : Int,iconColorId:Int){
        val res = v.context.resources
        v.background = res.getDrawable(backgroundId)
        val ivCartIcon = v.getChildAt(0) as AppCompatImageView
        val button = v.getChildAt(1) as AppCompatTextView
        button.setTextColor(res.getColor(textColorId))
        button.text = res.getString(stringResId)
        ivCartIcon.foreground.setTint(res.getColor(iconColorId))
    }
}