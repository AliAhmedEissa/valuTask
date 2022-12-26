package com.valu.shopapp.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.handledatawithstatflow.helpers.EventObserver
import com.google.android.material.snackbar.Snackbar
import com.valu.shopapp.R
import com.valu.shopapp.databinding.FragmentHomeBinding
import com.valu.shopapp.home.adapters.AllProductsAdapter
import com.valu.shopapp.home.models.ProductsModelItem
import com.valu.shopapp.home.viewModels.HomeViewModel
import com.valu.shopapp.utils.ViewsManager

class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    lateinit var snackBar: Snackbar
    private val productsAdapter by lazy {
        AllProductsAdapter(onProductClickCallback)
    }
    private val onProductClickCallback: (product: ProductsModelItem) -> Unit = { product ->
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        findNavController().navigate(R.id.action_to_productDetails, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getAllProducts()
        subscribeData()
    }

    private fun subscribeData() {
        lifecycleScope.launchWhenStarted {
            vm.allProducts.collect(EventObserver(
                onSuccess = {
                    (requireActivity() as ViewsManager).hideProgressBar()
                    binding.allProductsRecycler.apply {
                        layoutManager = GridLayoutManager(requireContext(), 2)
                        productsAdapter.setData(it)
                        adapter = productsAdapter
                    }
                },
                onError = {
                    showSnackbar(it)
                }, onLoading = {
                    (requireActivity() as ViewsManager).showProgressBar()
                }
            ))
        }
    }

    fun showSnackbar(error: String) {
        snackBar = Snackbar.make(requireView(), error, Snackbar.LENGTH_INDEFINITE)
            .setAction("TryAgain") {
                vm.getAllProducts()
                snackBar.dismiss()
            }
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
        snackBar.show()
    }


}