//features/agroclima/presentation/viewmodels/WeatherViewModel.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CalculateIrrigationUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckFrostAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckWindAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetCurrentWeatherUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetForecastUseCase


class WeatherViewModelFactory(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val checkFrostAlertUseCase: CheckFrostAlertUseCase,
    private val checkWindAlertUseCase: CheckWindAlertUseCase,
    private val calculateIrrigationUseCase: CalculateIrrigationUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> {
                WeatherViewModel(
                    getCurrentWeatherUseCase,
                    checkFrostAlertUseCase,
                    checkWindAlertUseCase,
                    calculateIrrigationUseCase
                ) as T
            }
            modelClass.isAssignableFrom(ForecastViewModel::class.java) -> {
                ForecastViewModel(
                    getForecastUseCase
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}