package com.example.weatherapp.application

import android.app.Application
import android.content.Context
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

class WeatherApplication : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()


        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()


    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is WeatherApplication -> appComponent
        else -> this.applicationContext.appComponent
    }










