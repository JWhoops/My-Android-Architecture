package com.example.myarchitecture.screens.albumlist

import com.example.myarchitecture.album.Album
import com.example.myarchitecture.screens.common.views.ObservableViewMvc

interface AlbumListViewMvc : ObservableViewMvc<AlbumListViewMvc.Listener> {

    interface Listener {
        fun onPostClicked(album: Album)
    }

    fun bindAlbums(albums: ArrayList<Album>)
}