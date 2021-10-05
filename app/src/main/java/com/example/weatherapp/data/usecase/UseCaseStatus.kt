package com.example.weatherapp.data.usecase

sealed class UseCaseStatus<T> {

    data class Success<T>(
        val value : T
    ): UseCaseStatus<T>()

    data class Error<T>(
        val throwable: Throwable
    ): UseCaseStatus<T>()

}