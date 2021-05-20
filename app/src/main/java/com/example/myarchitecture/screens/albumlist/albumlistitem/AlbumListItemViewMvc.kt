package com.example.myarchitecture.screens.albumlist.albumlistitem

import com.example.myarchitecture.album.Album
import com.example.myarchitecture.screens.common.views.ObservableViewMvc

interface AlbumListItemViewMvc : ObservableViewMvc<AlbumListItemViewMvc.Listener> {

    interface Listener {
        fun onPostClicked(album: Album)
    }

    fun bindPost(album: Album)
}