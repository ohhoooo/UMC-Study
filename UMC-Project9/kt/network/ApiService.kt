package com.kjh.umc_project9.network

import com.kjh.umc_project9.BuildConfig
import com.kjh.umc_project9.model.CookRecipe
import com.kjh.umc_project9.model.Ministry
import com.kjh.umc_project9.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/3072692/v1/uddi:9d420e87-8e70-4fb0-a54a-be1244249b2e_201909271427")
    fun getMinistryService(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("serviceKey") serviceKey: String,
    ): Call<Ministry>

    @GET("api/${BuildConfig.API_KEY2}/COOKRCP01/json/1/1")
    fun getRecipe(
    ): Call<CookRecipe>

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") serviceKey: String,
    ): Call<Weather>
}