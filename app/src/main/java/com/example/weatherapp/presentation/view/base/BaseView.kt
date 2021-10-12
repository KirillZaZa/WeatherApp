package com.example.weatherapp.presentation.view.base

import androidx.lifecycle.LifecycleOwner


interface BaseView: LifecycleOwner {
    fun toast(msg: String)
}