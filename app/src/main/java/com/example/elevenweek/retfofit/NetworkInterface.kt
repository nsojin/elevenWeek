package com.example.elevenweek.retfofit

import com.example.elevenweek.data.ImageData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkInterface {
    @GET("v2/search/image")
    fun Search(
        @Header("Authorization") Authorization : String?,
        @Query("query") query : String?,
        @Query("sort") sort : String?,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): ImageData
}