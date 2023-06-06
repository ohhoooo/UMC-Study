package com.kjh.umc_project9.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val service = ApiService::class.java

    private fun getRetrofitBuilder(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(baseUrl: String): ApiService {
        return getRetrofitBuilder(baseUrl).create(service)
    }
}