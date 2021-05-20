package com.example.myarchitecture.networking.albumphoto

import com.google.gson.annotations.SerializedName

data class AlbumPhotoSchema(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val photoUrl: String
)