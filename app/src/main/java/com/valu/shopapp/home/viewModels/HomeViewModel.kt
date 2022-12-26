package com.valu.shopapp.home.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handledatawithstatflow.helpers.Event
import com.example.handledatawithstatflow.helpers.Resource
import com.valu.shopapp.home.models.ProductsModelItem
import com.valu.shopapp.network.ApiManager
import com.valu.shopapp.network.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val apiManager = ApiManager().getClient()!!.create(WebServices::class.java)

    private val _allProducts =
        MutableStateFlow<Event<Resource<ArrayList<ProductsModelItem>>>>(Event(Resource.Loading()))

    val allProducts: MutableStateFlow<Event<Resource<ArrayList<ProductsModelItem>>>> = _allProducts


    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiManager.getAllProducts()
                if (response.isSuccessful) {
                    val allProducts = response.body()!!
                    _allProducts.emit(Event(Resource.Success(allProducts)))
                } else {
                    _allProducts.emit(Event(Resource.Error("Something went wrong...")))
                }
            } catch (error: Exception) {
                _allProducts.emit(Event(Resource.Error(error.localizedMessage)))
            }
        }
    }
}