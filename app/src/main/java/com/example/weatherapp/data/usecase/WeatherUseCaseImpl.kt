package com.example.weatherapp.data.usecase

import android.util.Log
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.data.local.entity.LocalWeatherHourly
import com.example.weatherapp.data.mapper.mapToHourly
import com.example.weatherapp.data.repository.Response
import com.example.weatherapp.data.mapper.toLocalWeather
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(private val repositoryImpl: WeatherRepositoryImpl)
    : WeatherUseCase{



    override fun getLocalWeatherData(
        cityName: String,
        callback: (ResponseUseCase<LocalWeatherData>) -> Unit
    ) {
        repositoryImpl.getWeatherFromOpenWeather(cityName) {
            when (it) {
                is Response.Success -> {
                    val localWeatherData = it.value.toLocalWeather()
                    callback(ResponseUseCase.Success(localWeatherData))

                }
                is Response.Error -> {
                    callback(ResponseUseCase.Error(it.throwable))
                }
            }
        }
    }

    override fun updateWeather(callback: (ResponseUseCase<LocalWeatherData>) -> Unit) {
        Log.e("Use case", "making request")
        repositoryImpl.updateWeather { response->
            when(response){
                is Response.Success -> {
                    val localWeatherData = response.value.toLocalWeather()
                    callback(ResponseUseCase.Success(localWeatherData))
                }

                is Response.Error -> {
                    callback(ResponseUseCase.Error(response.throwable))
                }

            }
        }
    }

    override fun getWeatherForecast(callback: (ResponseUseCase<List<LocalWeatherHourly>>) -> Unit) {
        repositoryImpl.getWeatherForecast { response ->
            when(response){
                is Response.Success -> {
                    val listHourly = response.value.hourly.mapToHourly()
                    callback(ResponseUseCase.Success(listHourly))
                }

                is Response.Error -> {
                    callback(ResponseUseCase.Error(response.throwable))
                }
            }
        }
    }


}