//features/agroclima/di/WeatherModule.kt
package com.edwindiaz.agroclima.features.agroclima.di

import com.edwindiaz.agroclima.core.di.AppContainer
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CalculateIrrigationUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckFrostAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckWindAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetCurrentWeatherUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetForecastUseCase
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

class WeatherModule(
    private val appContainer: AppContainer
) {

    private fun provideGetCurrentWeatherUseCase(): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(appContainer.weatherRepository)
    }
    private fun provideGetForecastUseCase(): GetForecastUseCase {
        return GetForecastUseCase(appContainer.weatherRepository)
    }
    private fun provideCheckFrostAlertUseCase(): CheckFrostAlertUseCase {
        return CheckFrostAlertUseCase()
    }

    private fun provideCheckWindAlertUseCase(): CheckWindAlertUseCase {
        return CheckWindAlertUseCase()
    }

    private fun provideCalculateIrrigationUseCase(): CalculateIrrigationUseCase {
        return CalculateIrrigationUseCase()
    }



    fun provideWeatherViewModelFactory(): WeatherViewModelFactory {
        return WeatherViewModelFactory(
            getCurrentWeatherUseCase = provideGetCurrentWeatherUseCase(),
            getForecastUseCase = provideGetForecastUseCase(),
            checkFrostAlertUseCase = provideCheckFrostAlertUseCase(),
            checkWindAlertUseCase = provideCheckWindAlertUseCase(),
            calculateIrrigationUseCase = provideCalculateIrrigationUseCase()
        )
    }
}