package com.example.weatherapp.presentation.view

import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.presentation.view.base.BaseView
import com.example.weatherapp.presentation.view.status.EventStatus

interface WeatherView: BaseView {

    fun showError(eventStatus: EventStatus)

    fun showWeatherData(localWeatherData: LocalWeatherData)

    override fun toast(msg: String)
}