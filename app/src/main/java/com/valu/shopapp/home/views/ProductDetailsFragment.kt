package com.valu.shopapp.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.valu.shopapp.R
import com.valu.shopapp.databinding.FragmentProductDetailsBinding
import com.valu.shopapp.home.models.ProductsModelItem

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var product: ProductsModelItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = requireArguments().getParcelable("product")!!
        initView()
    }

    private fun initView() {
        binding.category.text = product.category.toString()
        binding.title.text = product.title.toString()
        binding.price.text = getString(R.string.price,product.price.toString())
        binding.description.text = product.description.toString()
        binding.rate.text = getString(R.string.rate,product.rating!!.rate.toString())
        Glide.with(requireContext()).load(product.image).into(binding.image)
    }

}