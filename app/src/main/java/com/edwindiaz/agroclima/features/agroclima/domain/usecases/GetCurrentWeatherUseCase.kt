//features/agroclima/domain/usecases/GetCurrentWeatherUseCase.kt
package com.edwindiaz.agroclima.features.agroclima.domain.usecases

import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.repositories.WeatherRepository

class GetCurrentWeatherUseCase(
     private val repository: WeatherRepository
) {
    suspend operator fun invoke(location: Location): Result<Weather> {
        return try {
            val weather = repository.getCurrentWeather(location)
            Result.success(weather)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}