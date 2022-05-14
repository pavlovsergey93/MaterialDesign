package com.gmail.pavlovsv93.materialdesign.model
import com.google.gson.annotations.SerializedName

data class DataResponseNasaDTO(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdUrl")
    val hdurl: String,
    @SerializedName("mediaType")
    val mediaType: String,
    @SerializedName("serviceVersion")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

