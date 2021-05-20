package com.example.myarchitecture.screens.common.views

abstract class BaseObservableViewMvc<ListenerType> : ObservableViewMvc<ListenerType>, BaseViewMvc() {
    private val listeners = mutableSetOf<ListenerType>()

    final override fun registerListener(listener: ListenerType) {
        listeners.add(listener)
    }

    final override fun unregisterListener(listener: ListenerType) {
        listeners.remove(listener)
    }

    protected fun getListeners(): Set<ListenerType> = listeners
}