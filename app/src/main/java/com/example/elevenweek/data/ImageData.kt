package com.example.elevenweek.data

import com.google.gson.annotations.SerializedName

data class ImageData(val response: ImageResponse)

data class ImageResponse(
    @SerializedName("meta")
    val meta : Meta,
    @SerializedName("documents")
    val documents : Documents
)

data class Meta (
    @SerializedName("total_count")
    val totalCount : Int,

    @SerializedName("pageable_count")
    val pageableCount : Int,

    @SerializedName("is_end")
    val isEnd : Boolean
)

data class Documents(
    @SerializedName("collection")
    val collection : String,

    @SerializedName("thumbnail_url")
    val thumbnailUrl : String,

    @SerializedName("image_url")
    val imageUrl : String,

    @SerializedName("width")
    val width : Int,

    @SerializedName("height")
    val height : Int,

    @SerializedName("display_sitename")
    val displaySitename : String,

    @SerializedName("doc_url")
    val docUrl : String,

    @SerializedName("datetime")
    val datetime : String
)