package com.valu.shopapp.network

import com.google.gson.GsonBuilder
import com.valu.shopapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
        }
        return retrofit
    }

}
