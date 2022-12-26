package com.valu.shopapp.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class Rating(

	@field:SerializedName("rate")
	val rate: Float? = null,

	@field:SerializedName("count")
	val count: Int? = null
):Parcelable

@Parcelize
data class ProductsModelItem(

    @field:SerializedName("image")
	val image: String? = null,

    @field:SerializedName("price")
	val price: Double? = null,

    @field:SerializedName("rating")
	val rating: Rating? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("category")
	val category: String? = null
): Parcelable
data class ProductsModel(
	@field:SerializedName("productsModel")
	val productsModel: List<ProductsModelItem> = ArrayList()
)
