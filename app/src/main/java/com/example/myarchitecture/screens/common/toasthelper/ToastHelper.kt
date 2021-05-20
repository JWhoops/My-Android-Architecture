package com.example.myarchitecture.screens.common.toasthelper

import android.content.Context
import android.widget.Toast
import com.example.myarchitecture.common.dependencyinjection.controller.ControllerScope
import javax.inject.Inject

@ControllerScope
class ToastHelper @Inject constructor(
    private val context: Context
) {

    fun showNetworkCallError() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
    }

}
