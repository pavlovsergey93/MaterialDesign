package com.gmail.pavlovsv93.materialdesign.domain

import com.gmail.pavlovsv93.materialdesign.model.DataResponseNasaDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
    @GET("planetary/apod?")
    fun getPictureByDate(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<DataResponseNasaDTO>
}