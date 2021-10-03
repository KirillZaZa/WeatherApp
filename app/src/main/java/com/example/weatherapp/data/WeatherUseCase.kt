package com.example.weatherapp.data

import com.example.weatherapp.data.extension.mapToLocalWeatherList
import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.local.LocalWeatherDetails
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    private var localWeatherData: LocalWeatherData? = null

    fun getWeatherData(cityName: String): LocalWeatherData?{
        repository.getWeatherFromOpenWeather(cityName){
            localWeatherData = it
        }

        return localWeatherData
    }

    fun getForecastForDay(): List<LocalWeatherDetails>? = localWeatherData?.list?.mapToLocalWeatherList()

}