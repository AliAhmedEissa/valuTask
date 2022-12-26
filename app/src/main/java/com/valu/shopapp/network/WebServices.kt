package com.valu.shopapp.network


import com.valu.shopapp.home.models.ProductsModelItem
import retrofit2.Response
import retrofit2.http.*

interface WebServices {

    @GET("products")
    suspend fun getAllProducts(): Response<ArrayList<ProductsModelItem>>
}