package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.local.prefs.PrefsManager
import com.example.weatherapp.data.network.ApiConfig
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.data.network.entity.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherService,
    private val prefsManager: PrefsManager,
    private val compositeDisposable: CompositeDisposable) :
    WeatherRepository {


    override fun getWeatherFromOpenWeather(cityName: String, callback: (Response<WeatherData>) -> Unit) {
        val disposable = api.getForecastForCity(cityName, ApiConfig.API_KEY, ApiConfig.LANG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->

                prefsManager.setCityToPrefs(cityName)
                callback(Response.Success(weather))

            }, { error ->

                Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: ${error.localizedMessage}")
                callback(Response.Error(error))

            })

    }

    override fun updateWeather(callback: (Response<WeatherData>) -> Unit) {
        val cityName = prefsManager.getCityFromPrefs()

        Log.e("Repository", "making request")

          val disposable = api.getForecastForCity(cityName, ApiConfig.API_KEY, ApiConfig.LANG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weather->
                    Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: $weather")
                    callback(Response.Success(weather))

                }, { error->
                    Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: ${error.localizedMessage}")
                    callback(Response.Error(error))
                })

    }


}