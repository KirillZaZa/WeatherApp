package com.example.weatherapp.data.repository

sealed class Response<T>{

    data class Success<T>(
        val value: T
    ): Response<T>()

    data class Error<T>(
        val throwable: Throwable
    ): Response<T>()


}
