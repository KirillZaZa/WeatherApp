package com.example.weatherapp.presentation.view

import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.presentation.base.BaseView
import com.example.weatherapp.presentation.view.status.EventStatus

interface WeatherView: BaseView {

    fun showError(eventStatus: EventStatus)

    fun showWeatherData(localWeatherData: LocalWeatherData)

    fun showForecastForDay(localWeatherDataList: List<LocalWeatherDetails>)

    override fun toast(msg: String)
}