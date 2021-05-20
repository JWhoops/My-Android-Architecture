package com.example.myarchitecture.screens.albumlist.albumlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.myarchitecture.R
import com.example.myarchitecture.album.Album
import com.example.myarchitecture.screens.common.views.BaseObservableViewMvc

class AlbumListItemViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseObservableViewMvc<AlbumListItemViewMvc.Listener>(),
    AlbumListItemViewMvc {

    private lateinit var mAlbum: Album
    private var textView: TextView

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_post_list_item, parent, false))
        getRootView().setOnClickListener {
            getListeners().forEach { listener -> listener.onPostClicked(mAlbum) }
        }
        textView = findViewById(R.id.title_txt)
    }

    override fun bindPost(album: Album) {
        this.mAlbum = album
        textView.text = album.title
    }
}