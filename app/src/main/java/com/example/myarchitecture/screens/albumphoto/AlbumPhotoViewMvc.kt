package com.example.myarchitecture.screens.albumphoto

import com.example.myarchitecture.album.AlbumPhoto
import com.example.myarchitecture.screens.common.views.ObservableViewMvc

interface AlbumPhotoViewMvc : ObservableViewMvc<AlbumPhotoViewMvc.Listener> {

    interface Listener {
       fun onNavigateUpClicked()
    }

    fun bindPhoto(albumPhoto: AlbumPhoto)
}