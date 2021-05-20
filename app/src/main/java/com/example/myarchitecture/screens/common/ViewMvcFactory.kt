package com.example.myarchitecture.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myarchitecture.screens.albumlist.AlbumListViewMvc
import com.example.myarchitecture.screens.albumlist.AlbumListViewMvcImpl
import com.example.myarchitecture.screens.albumlist.albumlistitem.AlbumListItemViewMvc
import com.example.myarchitecture.screens.albumlist.albumlistitem.AlbumListItemViewMvcImpl
import com.example.myarchitecture.screens.albumphoto.AlbumPhotoViewMvc
import com.example.myarchitecture.screens.albumphoto.AlbumPhotoViewMvcImpl
import com.example.myarchitecture.screens.common.imageloader.ImageLoader
import com.example.myarchitecture.screens.common.toolbar.ToolbarViewMvc
import javax.inject.Inject
import javax.inject.Provider

class ViewMvcFactory @Inject constructor(
    private val layoutInflaterProvider: Provider<LayoutInflater>,
    private val imageLoaderProvider: Provider<ImageLoader>
) {

    fun getPostListViewMvc(parent: ViewGroup?): AlbumListViewMvc = AlbumListViewMvcImpl(layoutInflaterProvider.get(), parent, this)

    fun getPostListItemViewMvc(parent: ViewGroup?): AlbumListItemViewMvc = AlbumListItemViewMvcImpl(layoutInflaterProvider.get(), parent)

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc = ToolbarViewMvc(layoutInflaterProvider.get(), parent)

    fun getAlbumPhotoViewMvc(parent: ViewGroup?): AlbumPhotoViewMvc =
        AlbumPhotoViewMvcImpl(
            layoutInflaterProvider.get(),
            parent, imageLoaderProvider.get(),
            this
        )
}