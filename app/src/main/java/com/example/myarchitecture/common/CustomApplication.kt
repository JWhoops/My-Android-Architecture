package com.example.myarchitecture.common

import android.app.Application
import com.example.myarchitecture.common.dependencyinjection.app.AppComponent
import com.example.myarchitecture.common.dependencyinjection.app.DaggerAppComponent

class CustomApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }
}