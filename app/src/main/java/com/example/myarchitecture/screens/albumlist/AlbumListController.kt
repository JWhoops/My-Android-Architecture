package com.example.myarchitecture.screens.albumlist

import android.os.Parcelable
import com.example.myarchitecture.album.Album
import com.example.myarchitecture.album.FetchAlbumsUseCase
import com.example.myarchitecture.screens.common.screenNavigator.ScreenNavigator
import com.example.myarchitecture.screens.common.toasthelper.ToastHelper
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumListController @Inject constructor(
    private val fetchAlbumsUseCase: FetchAlbumsUseCase,
    private val screenNavigator: ScreenNavigator,
    private val toastHelper: ToastHelper
) : AlbumListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var isListLoaded = false

    private lateinit var viewMvc: AlbumListViewMvc

    fun bindView(albumListViewMvc: AlbumListViewMvc) {
        this.viewMvc = albumListViewMvc
    }

    fun onStart() {
        viewMvc.registerListener(this)
        if (!isListLoaded) {
            fetchAlbums()
        }
    }

    fun onStop() {
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    fun getStateToSave(): Parcelable = AlbumListControllerSavedState(isListLoaded)

    fun restoreState(savedState: Parcelable?) {
        val albumListControllerSavedState = savedState as AlbumListControllerSavedState
        val temp = albumListControllerSavedState.temp
    }

    override fun onPostClicked(album: Album) {
        screenNavigator.toAlbumPhoto(album.id)
    }

    private fun fetchAlbums() {
        coroutineScope.launch {
            val result = fetchAlbumsUseCase.fetchAlbums()
            when (result) {
                is FetchAlbumsUseCase.Result.Success -> {
                    isListLoaded = true
                    viewMvc.bindAlbums(result.albums)
                }
                is FetchAlbumsUseCase.Result.Failure -> {
                    onAlbumsFetchFail()
                }
            }
        }
    }

    private fun onAlbumsFetchFail() {
        toastHelper.showNetworkCallError()
    }

    @Parcelize
    data class AlbumListControllerSavedState(val temp: Boolean): Parcelable

    companion object {
        const val SAVED_STATE_KEY = "controller_saved_state_key"
    }
}