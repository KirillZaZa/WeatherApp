package com.example.weatherapp.presentation.presenters.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.weatherapp.presentation.view.base.BaseView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : BaseView>(
    private val compositeDisposable: CompositeDisposable
) : LifecycleObserver {

    protected var _context: Context? = null

    protected var _view: T? = null

    fun onCreate(context: Context, view: T) {
        _context = context

        _view = view
        _view!!.lifecycle.addObserver(this)
        Log.e("Abstract", "onCreate $_context, $_view, ${compositeDisposable.size()}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        _context = null

        _view!!.lifecycle.removeObserver(this)
        _view = null

        compositeDisposable.clear()
    }


}