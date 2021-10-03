package com.example.weatherapp.application

import android.app.Application
import android.content.Context
import com.example.weatherapp.di.DaggerWeatherComponent
import com.example.weatherapp.di.WeatherComponent

class WeatherApplication : Application() {


    private var _weatherComponent: WeatherComponent? = null

    val weatherComponent: WeatherComponent
        get() = checkNotNull(_weatherComponent)

    override fun onCreate() {
        super.onCreate()
        _weatherComponent = DaggerWeatherComponent.builder()
            .application(this)
            .create()

    }
}

val Context.weatherComponent: WeatherComponent
    get() = when (this) {
        is WeatherApplication -> weatherComponent
        else -> this.applicationContext.weatherComponent
    }

