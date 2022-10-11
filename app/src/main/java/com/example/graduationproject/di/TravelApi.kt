package com.example.graduationproject.di

import com.example.graduationproject.data.remote.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TravelApi {
    private const val BASE_URL = "https://633800d85327df4c43db5614.mockapi.io/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}