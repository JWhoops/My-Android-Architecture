package com.example.myarchitecture.common.dependencyinjection.controller

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides

@Module
object ControllerModule {

    @Provides
    fun context(activity: Activity): Context = activity

    @Provides
    fun layoutInflater(context: Context): LayoutInflater = LayoutInflater.from(context)

}