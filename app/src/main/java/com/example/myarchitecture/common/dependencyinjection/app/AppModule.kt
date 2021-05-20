package com.example.myarchitecture.common.dependencyinjection.app

import com.example.myarchitecture.common.Constants
import com.example.myarchitecture.networking.JsonPlaceHolderApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object AppModule {

    @AppScope
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @AppScope
    @Provides
    fun jsonPlaceHolderApi(retrofit: Retrofit): JsonPlaceHolderApi =
        retrofit.create(JsonPlaceHolderApi::class.java)

}