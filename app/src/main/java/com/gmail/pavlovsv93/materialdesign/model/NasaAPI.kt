package com.gmail.pavlovsv93.materialdesign.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NasaAPI {

    @GET("planetary/apod?")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String
    ): Call<DataResponseNasaDTO>

    @GET("planetary/apod?")
    fun getPictureByDate(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<DataResponseNasaDTO>


}