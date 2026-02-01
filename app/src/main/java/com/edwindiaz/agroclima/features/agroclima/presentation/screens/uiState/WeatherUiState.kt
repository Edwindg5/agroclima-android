package com.edwindiaz.agroclima.features.agroclima.presentation.screens.uiState

import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.entities.WeatherAlert
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CalculateIrrigationUseCase

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
    val alerts: List<WeatherAlert> = emptyList(),
    val irrigationRecommendation: CalculateIrrigationUseCase.IrrigationRecommendation? = null,
    val selectedLocation: Location? = null
)