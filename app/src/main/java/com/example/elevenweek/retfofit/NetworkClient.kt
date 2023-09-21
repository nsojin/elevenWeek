package com.example.elevenweek.retfofit

import com.example.elevenweek.Contain
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkClient {

    val api: NetworkInterface
        get() = instanse.create(NetworkInterface::class.java)

    private val instanse: Retrofit
        private get(){
            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(Contain.IMAGE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}