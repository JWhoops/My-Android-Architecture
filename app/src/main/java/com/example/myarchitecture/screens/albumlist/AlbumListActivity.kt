package com.example.myarchitecture.screens.albumlist

import android.os.Bundle
import android.os.PersistableBundle
import com.example.myarchitecture.screens.common.ViewMvcFactory
import com.example.myarchitecture.screens.common.controllers.BaseActivity
import javax.inject.Inject

class AlbumListActivity : BaseActivity() {

    @Inject
    lateinit var albumListController: AlbumListController

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        val viewMvc = viewMvcFactory.getPostListViewMvc(null)
        albumListController.bindView(viewMvc)

        savedInstanceState?.apply {
            albumListController.restoreState(getParcelable(AlbumListController.SAVED_STATE_KEY))
        }

        setContentView(viewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        albumListController.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val albumListControllerSavedState = albumListController.getStateToSave()
        outState.putParcelable(AlbumListController.SAVED_STATE_KEY, albumListControllerSavedState)
    }

    override fun onStop() {
        super.onStop()
        albumListController.onStop()
    }
}