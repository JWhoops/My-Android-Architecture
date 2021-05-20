package com.example.myarchitecture.screens.albumphoto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myarchitecture.album.FetchAlbumPhotoUseCase
import com.example.myarchitecture.screens.common.ViewMvcFactory
import com.example.myarchitecture.screens.common.controllers.BaseActivity
import com.example.myarchitecture.screens.common.toasthelper.ToastHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumPhotoActivity : BaseActivity(), AlbumPhotoViewMvc.Listener {

    @Inject
    lateinit var fetchAlbumPhotoUseCase: FetchAlbumPhotoUseCase

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var toastHelper: ToastHelper

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: AlbumPhotoViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        viewMvc = viewMvcFactory.getAlbumPhotoViewMvc(null)
        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchAlbumPhoto(getAlbumId())
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchAlbumPhoto(albumId: String) {
        coroutineScope.launch {
            val result = fetchAlbumPhotoUseCase.fetchAlbumPhoto(albumId)
            when (result) {
                is FetchAlbumPhotoUseCase.Result.Success -> {
                    viewMvc.bindPhoto(result.albumPhoto)
                }
                is FetchAlbumPhotoUseCase.Result.Failure -> {
                    onAlbumPhotoFetchFail()
                }
            }
        }
    }

    override fun onNavigateUpClicked() {
        onBackPressed()
    }

    private fun onAlbumPhotoFetchFail() {
        toastHelper.showNetworkCallError()
    }

    private fun getAlbumId(): String = intent.getStringExtra(EXTRA_ALBUM_ID) ?: "1"

    companion object {
        const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun start(context: Context, albumId: String) {
            val intent = Intent(context, AlbumPhotoActivity::class.java).apply {
                putExtra(EXTRA_ALBUM_ID, albumId)
            }
            context.startActivity(intent)
        }
    }
}