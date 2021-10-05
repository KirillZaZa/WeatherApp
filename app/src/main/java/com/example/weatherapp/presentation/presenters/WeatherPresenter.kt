package com.example.weatherapp.presentation.presenters


import com.example.weatherapp.data.usecase.UseCaseStatus
import com.example.weatherapp.data.usecase.WeatherUseCaseImpl
import com.example.weatherapp.presentation.base.BasePresenter
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import javax.inject.Inject


interface WeatherPresenter {

    fun onUpdateButtonClick(cityName: String?)

    fun searchButtonClick(cityName: String?)

}

class WeatherPresenterImpl @Inject constructor(
    private val currentView: WeatherView,
) : BasePresenter<WeatherView>(currentView), WeatherPresenter {

    @Inject
    private lateinit var weatherUseCase: WeatherUseCaseImpl

    override fun onUpdateButtonClick(cityName: String?) {
        cityName ?: currentView.showError(EventStatus.SearchError("Введите название города!"))
        weatherUseCase.getLocalWeatherData(cityName!!) { status ->
            when (status) {
                is UseCaseStatus.Success -> {
                    view.showWeatherData(status.value)
                    view.showForecastForDay(status.value.list)
                }

                is UseCaseStatus.Error -> {
                    view.showError(EventStatus.UpdateError("Ошибка при обновлении. Попробуйте снова"))
                }
            }
        }
    }

    override fun searchButtonClick(cityName: String?) {
        cityName ?: currentView.showError(EventStatus.SearchError("Введите название города!"))
        weatherUseCase.getLocalWeatherData(cityName!!) { status ->
            when(status){
                is UseCaseStatus.Success ->{
                    view.showWeatherData(status.value)
                    view.showForecastForDay(status.value.list)
                }

                is UseCaseStatus.Error -> {
                    view.showError(EventStatus.UpdateError("Ошибка при запросе. Попробуйте снова."))
                }
            }
        }
    }

    override fun onCreate() {}

    override fun onAttach() {}

    override fun onDetach() {}

    override fun onDestroy() {}


}