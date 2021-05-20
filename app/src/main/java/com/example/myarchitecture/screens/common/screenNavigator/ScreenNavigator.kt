package com.example.myarchitecture.screens.common.screenNavigator

import android.content.Context
import com.example.myarchitecture.common.dependencyinjection.controller.ControllerScope
import com.example.myarchitecture.screens.albumphoto.AlbumPhotoActivity
import javax.inject.Inject

@ControllerScope
class ScreenNavigator @Inject constructor(private val context: Context) {

    fun toAlbumPhoto(albumId: String) {
        AlbumPhotoActivity.start(context, albumId)
    }

}
