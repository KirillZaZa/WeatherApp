package com.example.weatherapp.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/hourly?q={cityname}")
    fun getForecastForCity(@Query("cityname") cityName: String): Single<WeatherData>

}