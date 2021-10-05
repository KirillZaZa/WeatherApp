package com.example.weatherapp.di

import android.app.Activity
import android.app.Application
import com.example.weatherapp.di.modules.WeatherModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface WeatherComponent {


    fun inject(activity: Activity)


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): WeatherComponent
    }

}

