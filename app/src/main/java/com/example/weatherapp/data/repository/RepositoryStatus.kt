package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.entity.WeatherData

sealed class RepositoryStatus<T>{

    data class Success<T>(
        val value: WeatherData
    ): RepositoryStatus<T>()

    data class Error<T>(
        val throwable: Throwable
    ): RepositoryStatus<T>()

}
