package com.valu.shopapp.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valu.shopapp.R
import com.valu.shopapp.databinding.ItemProductBinding
import com.valu.shopapp.home.models.ProductsModelItem

class AllProductsAdapter(
    private val onProductClickCallback: (product: ProductsModelItem) -> Unit
) : RecyclerView.Adapter<AllProductsAdapter.ViewHolder>() {
    private lateinit var binding: ItemProductBinding
    private var productsModelList = ArrayList<ProductsModelItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fillData(holder, position)
    }

    override fun getItemCount(): Int {
        return productsModelList.size
    }

    fun setData(productsList: ArrayList<ProductsModelItem>) {
        productsModelList = productsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: ItemProductBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var productTitle: TextView = itemView.idTitleTx
        var productPrice: TextView = itemView.idPrice
        var productImage: ImageView = itemView.idImage

    }

    private fun fillData(holder: ViewHolder, position: Int) {
        val product = productsModelList[position]
        holder.productTitle.text = product.title.toString().trim()
        holder.productPrice.text = holder.itemView.context.getString(R.string.price,product.price.toString())
        Glide.with(holder.itemView.context).load(product.image).into(holder.productImage)

        holder.itemView.setOnClickListener {
            onProductClickCallback.invoke(productsModelList[position])
        }
    }
}