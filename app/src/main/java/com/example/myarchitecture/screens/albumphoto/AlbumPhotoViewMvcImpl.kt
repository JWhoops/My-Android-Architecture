package com.example.myarchitecture.screens.albumphoto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.myarchitecture.R
import com.example.myarchitecture.album.AlbumPhoto
import com.example.myarchitecture.common.Constants
import com.example.myarchitecture.screens.common.ViewMvcFactory
import com.example.myarchitecture.screens.common.imageloader.ImageLoader
import com.example.myarchitecture.screens.common.toolbar.ToolbarViewMvc
import com.example.myarchitecture.screens.common.views.BaseObservableViewMvc

class AlbumPhotoViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val imageLoader: ImageLoader,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<AlbumPhotoViewMvc.Listener>(), AlbumPhotoViewMvc {

    private var photoImageView: ImageView
    private var titleTextView: TextView
    private var toolbar: Toolbar

    private var toolbarViewMvc: ToolbarViewMvc

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_album_photo, parent, false))
        photoImageView = findViewById(R.id.photo_image_view)
        titleTextView = findViewById(R.id.photo_title_txt)

        toolbar = findViewById(R.id.toolbar)
        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.addView(toolbarViewMvc.getRootView())
        toolbarViewMvc.setTitle("ALBUM PHOTO")
        toolbarViewMvc.enableUpButtonAndListen(object : ToolbarViewMvc.NavigateUpClickListener {
            override fun onNavigateUpClicked() {
                getListeners().forEach { listener ->
                    listener.onNavigateUpClicked()
                }
            }
        })
    }

    override fun bindPhoto(albumPhoto: AlbumPhoto) {
        titleTextView.text = albumPhoto.title
//        issue with server image, so use temp image url
        imageLoader.loadImage(Constants.tempImageUrl, photoImageView)
    }

}
