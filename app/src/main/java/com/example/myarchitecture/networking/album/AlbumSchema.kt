package com.example.myarchitecture.networking.album

import com.google.gson.annotations.SerializedName

data class AlbumSchema(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
)