package com.example.weatherapp.presentation.base

abstract class BasePresenter<T : BaseView>(protected val view: T){

    abstract fun onCreate()

    abstract fun onAttach()

    abstract fun onDetach()

    abstract fun onDestroy()

}