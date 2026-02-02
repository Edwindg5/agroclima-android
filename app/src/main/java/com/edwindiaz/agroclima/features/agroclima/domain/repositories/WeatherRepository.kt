//features/agroclima/domain/repositories/WeatherRepository.kt
package com.edwindiaz.agroclima.features.agroclima.domain.repositories

import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(location: Location): Weather
    suspend fun getForecast(location: Location): List<Forecast>  // CAMBIO AQUÍ
}