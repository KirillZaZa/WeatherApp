package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.data.network.entity.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherService) :
    WeatherRepository {

    override fun getWeatherFromOpenWeather(cityName: String, callback: (RepositoryStatus<WeatherData>) -> Unit) {
        api.getForecastForCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->

                callback(RepositoryStatus.Success(weather))

            }, { failed ->

                Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: ${failed.localizedMessage}")
                callback(RepositoryStatus.Error(failed))

            })
            .dispose()
    }

}