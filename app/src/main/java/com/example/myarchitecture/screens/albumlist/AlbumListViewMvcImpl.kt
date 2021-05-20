package com.example.myarchitecture.screens.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myarchitecture.R
import com.example.myarchitecture.album.Album
import com.example.myarchitecture.screens.common.ViewMvcFactory
import com.example.myarchitecture.screens.common.toolbar.ToolbarViewMvc
import com.example.myarchitecture.screens.common.views.BaseObservableViewMvc

class AlbumListViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<AlbumListViewMvc.Listener>(),
    AlbumListViewMvc,
    AlbumListRecyclerAdapter.OnPostClickListener {

    private var toolbarViewMvc: ToolbarViewMvc
    private var toolbar: Toolbar

    private var mAlbumListAdapter: AlbumListRecyclerAdapter
    private var postRecyclerView: RecyclerView

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_post_list, parent, false))
        mAlbumListAdapter = AlbumListRecyclerAdapter(this, viewMvcFactory)
        postRecyclerView = findViewById(R.id.albums_listview)
        postRecyclerView.layoutManager = LinearLayoutManager(getContext())
        postRecyclerView.adapter = mAlbumListAdapter

        toolbar = findViewById(R.id.toolbar)
        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar)
        toolbarViewMvc.setTitle(getString(R.string.album_activity_title))
        toolbar.addView(toolbarViewMvc.getRootView())
    }

    override fun bindAlbums(albums: ArrayList<Album>) {
        mAlbumListAdapter.bindPosts(albums)
    }

    override fun onPostClicked(album: Album) {
        getListeners().forEach { listener -> listener.onPostClicked(album) }
    }
}