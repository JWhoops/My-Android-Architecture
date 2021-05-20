package com.example.myarchitecture.screens.albumlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myarchitecture.album.Album
import com.example.myarchitecture.screens.albumlist.albumlistitem.AlbumListItemViewMvc
import com.example.myarchitecture.screens.common.ViewMvcFactory

class AlbumListRecyclerAdapter(private val onPostClickListener: OnPostClickListener, private val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<AlbumListRecyclerAdapter.ViewHolder>(),
    AlbumListItemViewMvc.Listener {

    interface OnPostClickListener {
        fun onPostClicked(album: Album)
    }

    class ViewHolder(val viewMvc: AlbumListItemViewMvc) : RecyclerView.ViewHolder(viewMvc.getRootView())

    private var posts = ArrayList<Album>()

    fun bindPosts(albums: ArrayList<Album>) {
        this.posts = albums
        notifyDataSetChanged()
    }

    override fun onPostClicked(album: Album) {
        onPostClickListener.onPostClicked(album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getPostListItemViewMvc(parent)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewMvc.bindPost(posts[position])
    }

    override fun getItemCount(): Int = posts.size

}
