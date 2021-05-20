package com.example.myarchitecture.album

import com.example.myarchitecture.networking.JsonPlaceHolderApi
import com.example.myarchitecture.networking.album.AlbumSchema
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAlbumsUseCase @Inject constructor(
    private val jsonPlaceHolderApi: JsonPlaceHolderApi
) {

    sealed class Result {
        data class Success(val albums: ArrayList<Album>) : Result()
        object Failure : Result()
    }

    suspend fun fetchAlbums(): Result =
        withContext(Dispatchers.IO) {
            try {
                val response = jsonPlaceHolderApi.fetchAlbums()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(schemaToAlbums(response.body()!!))
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

    private fun schemaToAlbums(albumSchemaList: ArrayList<AlbumSchema>): ArrayList<Album> =
        ArrayList(albumSchemaList.map { schema ->
            Album(schema.id, schema.title)
        })
}