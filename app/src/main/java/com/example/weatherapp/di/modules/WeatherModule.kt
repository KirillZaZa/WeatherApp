package com.example.weatherapp.di.modules


import android.app.Application
import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.data.network.ApiConfig
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.databinding.SearchBarBinding
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Scope
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, RxJavaModule::class])
abstract class WeatherModule{

    @Singleton
    @Binds
    abstract fun context(application: Application): Context

}

@Module
class NetworkModule{

    @Provides
    fun provideWeatherService(): WeatherService{
        val httpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.API_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }

}


@Module
object RxJavaModule{

    @Volatile
    private var compositeDisposable: CompositeDisposable? = null

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable{

        return compositeDisposable ?: synchronized(this){
            val comp = CompositeDisposable()
            compositeDisposable = comp
            comp
        }
    }

}



