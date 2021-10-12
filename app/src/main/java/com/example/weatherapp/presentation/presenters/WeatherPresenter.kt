package com.example.weatherapp.presentation.presenters


import com.example.weatherapp.R
import com.example.weatherapp.data.usecase.ResponseUseCase
import com.example.weatherapp.data.usecase.WeatherUseCaseImpl
import com.example.weatherapp.presentation.presenters.base.BasePresenter
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


interface WeatherPresenter {


    fun searchButtonClick(cityName: String?)

    fun updateWeather(isLoading: (Boolean) -> Unit)

}

class WeatherPresenterImpl @Inject constructor(

    private val weatherUseCase: WeatherUseCaseImpl,
    compositeDisposable: CompositeDisposable,

    ) : BasePresenter<WeatherView>(compositeDisposable), WeatherPresenter {





    override fun searchButtonClick(cityName: String?) {

        _context ?: return

        if (cityName == null) {
            _view!!.showError(
                EventStatus.SearchError(
                    _context!!.getString(R.string.enter_city_name_toast)
                )
            )

            return
        }

        weatherUseCase.getLocalWeatherData(cityName) { status ->
            when (status) {

                is ResponseUseCase.Success -> _view!!.showWeatherData(status.value)


                is ResponseUseCase.Error -> _view!!.showError(
                    EventStatus.UpdateError(
                        _context!!.getString(R.string.update_error_toast)
                    )
                )


                else -> return@getLocalWeatherData

            }
        }
    }

    override fun updateWeather(isLoading: (Boolean)-> Unit) {
        isLoading(true)
        weatherUseCase.updateWeather { response ->
            when (response) {
                is ResponseUseCase.Success -> {
                    _view!!.showWeatherData(response.value)
                    isLoading(false)
                }

                is ResponseUseCase.Error -> {
                    _view!!.showError(
                        EventStatus.UpdateError(
                            _context!!.getString(R.string.update_error_toast)
                        )
                    )
                    isLoading(false)

                }

                is ResponseUseCase.Failed -> {
                    _view!!.showError(
                        EventStatus.UpdateError(
                            _context!!.getString(R.string.enter_city_name_toast)
                        )
                    )
                    isLoading(false)

                }

            }
        }
    }



}