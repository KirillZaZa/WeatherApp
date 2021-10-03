package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.network.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherService) {

    fun getWeatherFromOpenWeather(cityName: String, callback: (LocalWeatherData) -> Unit){
        api.getForecastForCity(cityName)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .map {
                 it.toLocalWeather()
             }
             .subscribe ({
                 callback(it)
             },{
                 Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: ${it.localizedMessage}", )
             })
            .dispose()
    }

}