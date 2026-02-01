//features/agroclima/presentation/viewmodels/WeatherViewModel.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CalculateIrrigationUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckFrostAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckWindAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetCurrentWeatherUseCase

class WeatherViewModelFactory(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val checkFrostAlertUseCase: CheckFrostAlertUseCase,
    private val checkWindAlertUseCase: CheckWindAlertUseCase,
    private val calculateIrrigationUseCase: CalculateIrrigationUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                getCurrentWeatherUseCase,
                checkFrostAlertUseCase,
                checkWindAlertUseCase,
                calculateIrrigationUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}