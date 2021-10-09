package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.di.modules.WeatherModule
import com.example.weatherapp.presentation.WeatherActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface AppComponent {


    fun inject(activity: WeatherActivity)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }




}

