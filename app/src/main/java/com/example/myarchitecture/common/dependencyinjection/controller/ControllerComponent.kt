package com.example.myarchitecture.common.dependencyinjection.controller

import android.app.Activity
import com.example.myarchitecture.screens.albumlist.AlbumListActivity
import com.example.myarchitecture.screens.albumphoto.AlbumPhotoActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ControllerScope
@Subcomponent(modules = [ControllerModule::class])
interface ControllerComponent {

    fun inject(activity: AlbumListActivity)
    fun inject(activity: AlbumPhotoActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: Activity
        ): ControllerComponent
    }
}