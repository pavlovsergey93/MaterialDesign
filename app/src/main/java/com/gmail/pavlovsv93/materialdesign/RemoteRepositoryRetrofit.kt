package com.gmail.pavlovsv93.materialdesign

import com.gmail.pavlovsv93.materialdesign.model.NasaAPI
import com.gmail.pavlovsv93.materialdesign.utils.BASE_URI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepositoryRetrofit {

    // Создаем экземпляр ретрофита
    fun getRetrofitImpl():NasaAPI{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URI)              //задаем доменное имя
            .addConverterFactory(           //конвертация в gson ответа сервера
                GsonConverterFactory
                    .create(
                        GsonBuilder().
                        setLenient()
                            .create()
                    )
            )
            .build()
        return retrofit.create(NasaAPI::class.java)        // возвращаем ретрофит
    }

}