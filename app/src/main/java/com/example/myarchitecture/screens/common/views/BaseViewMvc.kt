package com.example.myarchitecture.screens.common.views

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

abstract class BaseViewMvc : ViewMvc {

    private lateinit var mRootView: View

    protected fun setRootView(rootView: View) {
        mRootView = rootView
    }

    protected fun <T : View> findViewById(viewId: Int): T = getRootView().findViewById(viewId)

    protected fun getContext(): Context = mRootView.context

    protected fun getString(@StringRes id: Int): String = getContext().getString(id)

    override fun getRootView(): View = mRootView

}