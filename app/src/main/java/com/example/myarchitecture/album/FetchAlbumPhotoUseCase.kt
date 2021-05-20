package com.example.myarchitecture.album

import com.example.myarchitecture.networking.JsonPlaceHolderApi
import com.example.myarchitecture.networking.albumphoto.AlbumPhotoSchema
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAlbumPhotoUseCase @Inject constructor(
    private val jsonPlaceHolderApi: JsonPlaceHolderApi
) {

    sealed class Result {
        data class Success(val albumPhoto: AlbumPhoto) : Result()
        object Failure : Result()
    }

    suspend fun fetchAlbumPhoto(albumId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = jsonPlaceHolderApi.fetchAlbumPhoto(albumId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(schemaToAlbumPhoto(response.body()!![0]))
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

    private fun schemaToAlbumPhoto(albumPhotoSchema: AlbumPhotoSchema): AlbumPhoto =
        albumPhotoSchema.run { AlbumPhoto(id, title, photoUrl) }
}