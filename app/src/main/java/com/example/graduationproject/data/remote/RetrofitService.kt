package com.example.graduationproject.data.remote

import com.example.graduationproject.model.general.GeneralModel
import com.example.graduationproject.model.guidecategories.GuideCategoriesModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("AllTravelList")
    fun getTravel(): Call<List<GeneralModel>>

    @GET("GuideCategories")
    fun getTravel2(): Call<List<GuideCategoriesModel>>
}