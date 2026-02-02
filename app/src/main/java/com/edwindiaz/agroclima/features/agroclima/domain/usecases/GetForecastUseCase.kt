//features/agroclima/domain/usecases/GetForecastUseCase.kt

package com.edwindiaz.agroclima.features.agroclima.domain.usecases

import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.repositories.WeatherRepository

class GetForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(location: Location): Result<List<Forecast>> {
        return try {
            val forecasts = repository.getForecast(location)
            Result.success(forecasts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}