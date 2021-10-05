package com.example.weatherapp.presentation.view.status

sealed class EventStatus{
    abstract val msg: String


    data class SearchError(
        override val msg: String
    ): EventStatus()

    data class UpdateError(
        override val msg: String
    ): EventStatus()


}
