package com.example.elevenweek.retfofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkClient {
    private const val IMAGE_BASE_URL = "https://dapi.kakao.com/v2/search/image"

    val api: NetworkInterface
        get() = instanse.create(NetworkInterface::class.java)

    private val instanse: Retrofit
        private get(){
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(IMAGE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}