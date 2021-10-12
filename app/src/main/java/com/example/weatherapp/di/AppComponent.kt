package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.di.modules.WeatherModule
import com.example.weatherapp.presentation.WeatherActivity
import com.example.weatherapp.presentation.presenters.base.BasePresenter
import com.example.weatherapp.presentation.view.base.BaseView
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface AppComponent {


    fun inject(activity: WeatherActivity)

    fun inject(basePresenter: BasePresenter<BaseView>)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }




}

