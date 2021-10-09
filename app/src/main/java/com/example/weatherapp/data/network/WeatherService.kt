package com.example.weatherapp.data.network


import com.example.weatherapp.data.network.entity.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherService {

    @GET("weather")
    fun getForecastForCity(
        @Query("q") city_name: String,
        @Query("appid") api_key: String,
        @Query("lang") lang: String
    ): Single<WeatherData>

}