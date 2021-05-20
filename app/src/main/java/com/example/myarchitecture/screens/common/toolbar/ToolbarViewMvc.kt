package com.example.myarchitecture.screens.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.myarchitecture.R
import com.example.myarchitecture.screens.common.views.BaseViewMvc

class ToolbarViewMvc(layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvc() {

    interface NavigateUpClickListener {
        fun onNavigateUpClicked()
    }

    private var titleTextView: TextView
    private var backImageBtn: ImageButton

    private lateinit var navigateUpClickListener: NavigateUpClickListener

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_toolbar, parent, false))
        titleTextView = findViewById(R.id.toolbar_title_txt)
        backImageBtn = findViewById(R.id.back_btn)
        backImageBtn.setOnClickListener {
            navigateUpClickListener.onNavigateUpClicked()
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun enableUpButtonAndListen(navigateUpClickListener: NavigateUpClickListener) {
        backImageBtn.visibility = View.VISIBLE
        this.navigateUpClickListener = navigateUpClickListener
    }
}