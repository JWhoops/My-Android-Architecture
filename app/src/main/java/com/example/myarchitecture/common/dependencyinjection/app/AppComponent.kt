package com.example.myarchitecture.common.dependencyinjection.app

import android.app.Application
import com.example.myarchitecture.common.dependencyinjection.controller.ControllerComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newControllerComponent(): ControllerComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}