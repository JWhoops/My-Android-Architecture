package com.example.myarchitecture.networking

import com.example.myarchitecture.networking.album.AlbumSchema
import com.example.myarchitecture.networking.albumphoto.AlbumPhotoSchema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceHolderApi {

    @GET("/albums")
    suspend fun fetchAlbums(): Response<ArrayList<AlbumSchema>>

    @GET("/photos")
    suspend fun fetchAlbumPhoto(@Query("albumId") albumId: String): Response<ArrayList<AlbumPhotoSchema>>

}