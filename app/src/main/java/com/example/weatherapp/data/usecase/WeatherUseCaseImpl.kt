package com.example.weatherapp.data.usecase

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.extension.mapToLocalWeatherList
import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.repository.RepositoryStatus
import com.example.weatherapp.data.mapper.toLocalWeather
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(private val repositoryImpl: WeatherRepositoryImpl)
    : WeatherUseCase{



    override fun getLocalWeatherData(
        cityName: String,
        callback: (UseCaseStatus<LocalWeatherData>) -> Unit
    ) {
        repositoryImpl.getWeatherFromOpenWeather(cityName) {
            when (it) {
                is RepositoryStatus.Success -> {

                    val localWeatherData = it.value.toLocalWeather()
                    localWeatherData.list.mapToLocalWeatherList()

                    callback(UseCaseStatus.Success(localWeatherData))

                }
                is RepositoryStatus.Error -> {
                    callback(UseCaseStatus.Error(it.throwable))
                }
            }
        }
    }

}