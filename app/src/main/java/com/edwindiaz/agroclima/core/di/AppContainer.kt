//core/di/AppContainer.kt
package com.edwindiaz.agroclima.core.di

import android.content.Context
import com.edwindiaz.agroclima.core.network.WeatherApi
import com.edwindiaz.agroclima.features.agroclima.data.repositories.WeatherRepositoryImpl
import com.edwindiaz.agroclima.features.agroclima.domain.repositories.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    // Configuración básica temporal - Luego leeremos de BuildConfig
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val apiKey = "eaf042903d7d59a32a92c91b163c4780"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepositoryImpl(weatherApi, apiKey)
    }
}