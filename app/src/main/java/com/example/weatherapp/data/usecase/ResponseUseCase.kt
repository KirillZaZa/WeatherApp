package com.example.weatherapp.data.usecase

sealed class ResponseUseCase<T> {

    data class Success<T>(
        val value : T
    ): ResponseUseCase<T>()

    data class Error<T>(
        val throwable: Throwable
    ): ResponseUseCase<T>()

    data class Failed<T>(
        val throwable: Throwable
    ): ResponseUseCase<T>()

}