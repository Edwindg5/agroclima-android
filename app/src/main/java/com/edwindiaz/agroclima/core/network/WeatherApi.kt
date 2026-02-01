//core/network/WeatherApi.kt
package com.edwindiaz.agroclima.core.network

import com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.model.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = "",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): WeatherResponseDto

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = "",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): WeatherResponseDto
}