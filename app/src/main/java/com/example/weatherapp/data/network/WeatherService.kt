package com.example.weatherapp.data.network


import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.network.entity.WeatherHourly
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("weather")
    fun getForecastForCity(
        @Query("q") city_name: String,
        @Query("appid") api_key: String,
        @Query("lang") lang: String
    ): Single<WeatherData>

    @GET("onecall")
    fun getHourlyForecast(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("exclude") exclude: String,
        @Query("lang") lang: String,
        @Query("appid") api_key: String
    ): Single<WeatherHourly>
}