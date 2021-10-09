package com.example.weatherapp.presentation.presenters.base

import android.content.Context
import com.example.weatherapp.presentation.view.base.BaseView

abstract class BasePresenter<T : BaseView>(){

    protected var _context: Context? = null

    protected var _view: T? = null

    fun onAttach(context: Context, view: T){
        _context = context
        _view = view
    }

    fun onDetach(){
        _context = null
        _view = null
    }



}