package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.local.prefs.PrefsManager
import com.example.weatherapp.data.network.ApiConfig
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.network.entity.WeatherHourly
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherService,
    private val prefsManager: PrefsManager,
    private val compositeDisposable: CompositeDisposable,
) :
    WeatherRepository {


    override fun getWeatherFromOpenWeather(
        cityName: String,
        callback: (Response<WeatherData>) -> Unit
    ) {
        val disposable = api.getForecastForCity(cityName, ApiConfig.API_KEY, ApiConfig.LANG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->

                prefsManager.setCityToPrefs(cityName)
                prefsManager.setLatToPrefs(weather.coord.lat.toString())
                prefsManager.setLonFromPrefs(weather.coord.lon.toString())

                callback(Response.Success(weather))

            }, { error ->

                callback(Response.Error(error))

            })

        compositeDisposable.add(disposable)


    }

    override fun updateWeather(callback: (Response<WeatherData>) -> Unit) {
        val cityName = prefsManager.getCityFromPrefs()


        val disposable = api.getForecastForCity(cityName, ApiConfig.API_KEY, ApiConfig.LANG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather ->
                callback(Response.Success(weather))

            }, { error ->
                callback(Response.Error(error))
            })

        compositeDisposable.add(disposable)

    }

    override fun getWeatherForecast(callback: (Response<WeatherHourly>) -> Unit) {
        val lat = prefsManager.getLatFromPrefs()
        val lon = prefsManager.getLonFromPrefs()

        val disposable =
            api.getHourlyForecast(
                lat,
                lon,
                ApiConfig.EXCLUDE,
                ApiConfig.LANG,
                ApiConfig.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .delay(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecast->

                    Log.e("WeatherRepository: ", "getWeatherForecast: $forecast")

                    callback(Response.Success(forecast))

                }, { error->
                    Log.e("WeatherRepository: ", "getWeatherFromOpenWeather: ${error.localizedMessage}")
                    callback(Response.Error(error))
                })

        compositeDisposable.add(disposable)
    }



}