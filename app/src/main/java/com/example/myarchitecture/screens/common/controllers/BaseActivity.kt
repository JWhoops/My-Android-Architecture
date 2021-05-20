package com.example.myarchitecture.screens.common.controllers

import androidx.appcompat.app.AppCompatActivity
import com.example.myarchitecture.common.CustomApplication

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as CustomApplication).appComponent

    private val controllerComponent by lazy {
        appComponent.newControllerComponent().create(
            this
        )
    }

    protected val injector get() = controllerComponent
}